package src.View;

import javax.swing.*;
import javax.swing.table.*;
import src.Controller.SejourController;
import src.Controller.ReservationController;
import src.Controller.ClientController;
import src.Controller.ChambreController;
import src.config.*;

import src.Model.Sejour;
import src.Model.Reservation;
import src.Model.Client;
import src.Model.Chambre;

import src.View.outil.ButtonColumn;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class SejourPanel extends JPanel {
    private JTable sejourTable;
    private DefaultTableModel model;
    private JTextField searchSejourField;
    private JButton searchSejourButton;
    private JButton addSejourButton;

    private List<Integer> sejourIds;

    public SejourPanel() {
        setLayout(new BorderLayout());

        sejourIds = new ArrayList<>();

        
        searchSejourField = new JTextField(15);
        searchSejourButton = new JButton("Rechercher");
        addSejourButton = new JButton("Ajouter Séjour");

        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JLabel("Chercher séjour :"));
        centerPanel.add(searchSejourField);
        centerPanel.add(searchSejourButton);
        topPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(addSejourButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        
        String[] columnNames = {"Client", "Chambre", "Date Début", "Date Fin", "Supprimer"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                
                return column == 4;
            }
        };

        sejourTable = new JTable(model);
        add(new JScrollPane(sejourTable), BorderLayout.CENTER);

        
        Action deleteAction = new AbstractAction("Supprimer") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                int sejourId = sejourIds.get(row);

                int confirm = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment supprimer ce séjour ?",
                    "Confirmation suppression",
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    SejourController.supprimerSejour(sejourId);
                    loadSejours();
                }
            }
        };

        new ButtonColumn(sejourTable, deleteAction, 4).setText("Annuler");

       
        addSejourButton.addActionListener(this::showAddSejourDialog);
        searchSejourButton.addActionListener(e -> rechercherSejours());

        loadSejours();
    }

    private void rechercherSejours() {
        String recherche = searchSejourField.getText().trim().toLowerCase();
        model.setRowCount(0);
        sejourIds.clear();

        for (Sejour s : db.getInstance().sejours) {
            if (s == null) continue;

            Reservation r = ReservationController.getReservationById(s.getReservationId());
            if (r == null) continue;

            Client c = ClientController.getClient(r.getClientId());
            Chambre chambre = ChambreController.getChambre(r.getChambresId().get(0));

            String nomClient = (c != null ? (c.getNom() + " " + c.getPrenom()).toLowerCase() : "");
            if (nomClient.contains(recherche)) {
                model.addRow(new Object[]{
                        c != null ? c.getNom() + " " + c.getPrenom() : "Inconnu",
                        chambre != null ? chambre.toString() : "Inconnue",
                        s.getDateDebut(),
                        s.getDateFin() != null ? s.getDateFin() : "En cours",
                        "Supprimer"
                });
                sejourIds.add(s.getId());
            }
        }
    }

    private void showAddSejourDialog(ActionEvent e) {
        List<Reservation> reservations = ReservationController.getReservationsByHotelId(0);

        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune réservation disponible.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JComboBox<Reservation> reservationBox = new JComboBox<>(reservations.toArray(new Reservation[0]));
        reservationBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Reservation) {
                    Reservation r = (Reservation) value;
                    Client c = ClientController.getClient(r.getClientId());
                    setText((c != null ? c.getNom() + " " + c.getPrenom() : "Inconnu") + " - Début: " + r.getDateDebut());
                }
                return this;
            }
        });

        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(new JLabel("Choisir une réservation :"));
        panel.add(reservationBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Ajouter un séjour",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Reservation selectedReservation = (Reservation) reservationBox.getSelectedItem();
            if (selectedReservation != null) {
                SejourController.sejourner(selectedReservation.getId());
                loadSejours();
                JOptionPane.showMessageDialog(this, "Séjour ajouté avec succès !");
            }
        }
    }

    private void loadSejours() {
        model.setRowCount(0);
        sejourIds.clear();

        List<Sejour> sejours = db.getInstance().sejours;
        for (Sejour s : sejours) {
            if (s == null) continue;

            Reservation r = ReservationController.getReservationById(s.getReservationId());
            if (r == null) continue;

            Client c = ClientController.getClient(r.getClientId());
            Chambre chambre = ChambreController.getChambre(r.getChambresId().get(0));

            model.addRow(new Object[]{
                    c != null ? c.getNom() + " " + c.getPrenom() : "Inconnu",
                    chambre != null ? chambre.toString() : "Inconnue",
                    s.getDateDebut(),
                    s.getDateFin() != null ? s.getDateFin() : "En cours",
                    "Supprimer"
            });
            sejourIds.add(s.getId());
        }
    }
}
