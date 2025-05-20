package src.View;

import src.Controller.ChambreController;
import src.Controller.ClientController;
import src.Controller.ReservationController;
import src.Model.Client;
import src.Model.Chambre;
import src.Model.Reservation;
import src.View.outil.ButtonColumn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class ReservationPanel extends JPanel {

    private JTextField searchReservationField;
    private JButton searchReservationButton;
    private JButton addReservationButton;
    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public ReservationPanel() {
        setLayout(new BorderLayout());

        
        searchReservationField = new JTextField(15);
        searchReservationButton = new JButton("Rechercher");
        addReservationButton = new JButton("Ajouter Réservation");

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JLabel("Chercher réservation:"));
        centerPanel.add(searchReservationField);
        centerPanel.add(searchReservationButton);
        topPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(addReservationButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"ID", "Client", "Chambre", "Date Début", "Date Fin", "Supprimer"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5;
            }
        };

        reservationTable = new JTable(tableModel);
        reservationTable.setRowHeight(30);
        reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reservationTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(reservationTable);
        add(scrollPane, BorderLayout.CENTER);

        
        new ButtonColumn(reservationTable, deleteAction, 5).setText("Supprimer");

        
        loadReservations();

        
        addReservationButton.addActionListener(this::showAddReservationDialog);
    }

    private void loadReservations() {
        tableModel.setRowCount(0);
        for (Reservation r : ReservationController.getReservationsByHotelId(0)) {
            String clientName = getClientName(r.getClientId());
            int chambreId = r.getChambresId().get(0);
            Chambre chambre = ChambreController.getChambre(chambreId);
            String chambreAffichage = chambre != null ? chambre.toString() : "Inconnue";

            tableModel.addRow(new Object[]{
                    r.getId(),
                    clientName,
                    chambreAffichage,
                    r.getDateDebut(),
                    r.getDateFin(),
                    "Supprimer"
            });
        }
    }

    private String getClientName(int clientId) {
        Client c = ClientController.getClient(clientId);
        return c != null ? c.getNom() + " " + c.getPrenom() : "Inconnu";
    }

    private void showAddReservationDialog(ActionEvent e) {
        List<Client> clients = ClientController.getAllClients();
        List<Chambre> chambres = ChambreController.getAllChambres();

        if (clients.isEmpty() || chambres.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun client ou chambre disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));

        JComboBox<Client> clientBox = new JComboBox<>(clients.toArray(new Client[0]));
        JComboBox<Chambre> chambreBox = new JComboBox<>(chambres.toArray(new Chambre[0]));
        JTextField dateDebutField = new JTextField();
        JTextField dateFinField = new JTextField();

        panel.add(new JLabel("Sélectionnez un client :"));
        panel.add(clientBox);
        panel.add(new JLabel("Sélectionnez une chambre :"));
        panel.add(chambreBox);
        panel.add(new JLabel("Date de début (ex: 2024-06-10) :"));
        panel.add(dateDebutField);
        panel.add(new JLabel("Date de fin (ex: 2024-06-12) :"));
        panel.add(dateFinField);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Ajouter une réservation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Client selectedClient = (Client) clientBox.getSelectedItem();
            Chambre selectedChambre = (Chambre) chambreBox.getSelectedItem();
            String dateDebutStr = dateDebutField.getText().trim();
            String dateFinStr = dateFinField.getText().trim();

            if (selectedClient == null || selectedChambre == null ||
                    dateDebutStr.isEmpty() || dateFinStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LocalDate dateDebut = LocalDate.parse(dateDebutStr);
                LocalDate dateFin = LocalDate.parse(dateFinStr);

                if (dateDebut.isAfter(dateFin)) {
                    JOptionPane.showMessageDialog(this, "La date de début doit être avant la date de fin.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Reservation reservation = ReservationController.creatReservation(
                        selectedClient.getId(),
                        List.of(selectedChambre.getId()),
                        dateDebut,
                        dateFin
                );

                if (reservation != null) {
                    loadReservations();
                    JOptionPane.showMessageDialog(this, "Réservation ajoutée !");
                } else {
                    JOptionPane.showMessageDialog(this, "La chambre est déjà réservée pour cette période.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Format de date invalide.\nUtilisez YYYY-MM-DD", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

   
    Action deleteAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            int row = Integer.parseInt(e.getActionCommand());
            int id = (int) tableModel.getValueAt(row, 0);

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Êtes-vous sûr de vouloir supprimer cette réservation ?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    ReservationController.suprimReservation(id);
                    loadReservations();
                    JOptionPane.showMessageDialog(null, "Réservation supprimée avec succès !");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erreur lors de la suppression : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };
}
