package src.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import src.Controller.ClientController;
import src.Model.Client;
import src.View.outil.ButtonColumn;

public class ClientPanel extends JPanel {
    private JTable clientTable;
    private DefaultTableModel tableModel;
    private JTextField searchClientField;
    private JButton addClientButton;
    private JButton searchClientButton;

    public ClientPanel(int hotelId) {
        this.setLayout(new BorderLayout());

        setupTopPanel();
        setupTable();
        setupActions(hotelId);

        loadClientsData(hotelId);
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchClientField = new JTextField(15);
        searchClientButton = new JButton("Rechercher");
        centerPanel.add(new JLabel("Chercher client:"));
        centerPanel.add(searchClientField);
        centerPanel.add(searchClientButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addClientButton = new JButton("Ajouter Client");
        rightPanel.add(addClientButton);

        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupTable() {
        String[] columns = {"ID", "Nom", "Prénom", "Email", "Téléphone", "Modifier", "Supprimer"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6;
            }
        };

        clientTable = new JTable(tableModel);
        customizeTableAppearance();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void customizeTableAppearance() {
        clientTable.setRowHeight(30);
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientTable.setAutoCreateRowSorter(true);

        // Center alignment for most columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 5; i++) {
            clientTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void setupActions(int hotelId) {
        addClientButton.addActionListener(e -> showAddClientDialog(hotelId));

        Action modifyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                int clientId = (int) tableModel.getValueAt(row, 0);
                editClient(clientId, row);
            }
        };

        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                int clientId = (int) tableModel.getValueAt(row, 0);
                deleteClient(clientId, row);
            }
        };

        new ButtonColumn(clientTable, modifyAction, 5).setText("Modifier");
        new ButtonColumn(clientTable, deleteAction, 6).setText("Supprimer");

        searchClientButton.addActionListener(e -> searchClient(hotelId));
    }

    private void loadClientsData(int hotelId) {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                tableModel.setRowCount(0);
                List<Client> clients = ClientController.getClientsByHotelId(hotelId);
                for (Client client : clients) {
                    tableModel.addRow(new Object[]{
                            client.getId(),
                            client.getNom(),
                            client.getPrenom(),
                            client.getEmail(),
                            client.getTelephone(),
                            "Modifier",
                            "Supprimer"
                    });
                }
                return null;
            }
        };
        worker.execute();
    }

    private void showAddClientDialog(int hotelId) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Ajouter un client");
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setModal(true);

        JTextField nomField = new JTextField();
        JTextField prenomField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telField = new JTextField();

        dialog.add(new JLabel("Nom:")); dialog.add(nomField);
        dialog.add(new JLabel("Prénom:")); dialog.add(prenomField);
        dialog.add(new JLabel("Email:")); dialog.add(emailField);
        dialog.add(new JLabel("Téléphone:")); dialog.add(telField);

        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        saveBtn.addActionListener(e -> {
            if (validateFields(nomField.getText(), emailField.getText(), telField.getText())) {
                ClientController.createClient(hotelId, nomField.getText(), prenomField.getText(), emailField.getText(), telField.getText());
                loadClientsData(hotelId);
                dialog.dispose();
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        dialog.add(btnPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unused")
    private void editClient(int clientId, int row) {
        Client client = ClientController.getClient(clientId);
        if (client == null) return;

        JDialog dialog = new JDialog();
        dialog.setTitle("Modifier client");
        dialog.setLayout(new GridLayout(5, 2, 10, 10));
        dialog.setModal(true);

        JTextField nomField = new JTextField(client.getNom());
        JTextField prenomField = new JTextField(client.getPrenom());
        JTextField emailField = new JTextField(client.getEmail());
        JTextField telField = new JTextField(client.getTelephone());

        dialog.add(new JLabel("Nom:")); dialog.add(nomField);
        dialog.add(new JLabel("Prénom:")); dialog.add(prenomField);
        dialog.add(new JLabel("Email:")); dialog.add(emailField);
        dialog.add(new JLabel("Téléphone:")); dialog.add(telField);

        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        saveBtn.addActionListener(e -> {
            if (validateFields(nomField.getText(), emailField.getText(), telField.getText())) {
                ClientController.updateClient(clientId, nomField.getText(), prenomField.getText(), emailField.getText(), telField.getText());
                loadClientsData(client.getHotelId());
                dialog.dispose();
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        dialog.add(btnPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unused")
    private void deleteClient(int clientId, int row) {
    int confirm = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce client ?");
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            // Get hotelId before deletion (after deletion, client might be null)
            Client client = ClientController.getClient(clientId);
            if (client == null) {
                JOptionPane.showMessageDialog(this, "Client introuvable !");
                return;
            }

            int hotelId = client.getHotelId();

            // Delete from database/controller
            String result = ClientController.deleteClient(clientId);

            // Show success message
            JOptionPane.showMessageDialog(this, result);

            // Reload data for the current hotel
            loadClientsData(hotelId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppression", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    private void searchClient(int hotelId) {
        String text = searchClientField.getText().trim().toLowerCase();
        if (text.isEmpty()) {
            loadClientsData(hotelId);
            return;
        }

        List<Client> clients = ClientController.getClientsByHotelId(hotelId);
        tableModel.setRowCount(0);

        for (Client client : clients) {
            if (
                client.getNom().toLowerCase().contains(text) ||
                client.getPrenom().toLowerCase().contains(text) ||
                client.getEmail().toLowerCase().contains(text) ||
                client.getTelephone().toLowerCase().contains(text)
            ) {
                tableModel.addRow(new Object[]{
                        client.getId(),
                        client.getNom(),
                        client.getPrenom(),
                        client.getEmail(),
                        client.getTelephone(),
                        "Modifier",
                        "Supprimer"
                });
            }
        }
    }

    private boolean validateFields(String... fields) {
        for (String field : fields) {
            if (field.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs sont obligatoires !");
                return false;
            }
        }
        return true;
    }
}