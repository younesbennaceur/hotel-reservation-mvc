package src.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import src.Controller.ConsommationController;
import src.Controller.SejourController;
import src.Model.Consommation;
import src.Model.Sejour;

public class ConsommationPanel extends JPanel {
    private JTable consommationTable;
    private DefaultTableModel tableModel;
    private JTextField sejourIdField;
    private JButton searchButton;
    private JButton addConsommationButton;

    public ConsommationPanel() {
        this.setLayout(new BorderLayout());

        setupTopPanel();
        setupTable();
        setupActions();
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("ID Séjour :"));
        sejourIdField = new JTextField(10);
        topPanel.add(sejourIdField);

        searchButton = new JButton("Rechercher");
        topPanel.add(searchButton);

        addConsommationButton = new JButton("Ajouter Consommation");
        topPanel.add(addConsommationButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupTable() {
        String[] columns = {"ID", "ID Séjour", "Produit", "Prix (€)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Aucun champ éditable dans le tableau
            }
        };

        consommationTable = new JTable(tableModel);
        consommationTable.setRowHeight(30);

        // Centrer les données dans les colonnes
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            consommationTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(consommationTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void setupActions() {
        searchButton.addActionListener(e -> {
            String sejourIdText = sejourIdField.getText();
            try {
                int sejourId = Integer.parseInt(sejourIdText);
                loadConsommations(sejourId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un ID séjour valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        addConsommationButton.addActionListener(e -> {
            // Affiche une fenêtre popup pour ajouter une consommation avec JComboBox pour le séjour
            showAddConsommationDialog();
        });
    }

    private void loadConsommations(int sejourId) {
        tableModel.setRowCount(0); // Vide le tableau
        List<Consommation> consommations = ConsommationController.getConsommationsBySejourId(sejourId);
        for (Consommation c : consommations) {
            tableModel.addRow(new Object[]{
                    c.getId(),
                    c.getSejourId(),
                    c.getType(),
                    c.getMontant()
            });
        }
    }

    private void showAddConsommationDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Ajouter Consommation", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        // Récupérer tous les séjours (adapter selon ton code)
        List<Sejour> sejours = SejourController.getAllSejours();

        // JComboBox des séjours
        JComboBox<Sejour> sejourComboBox = new JComboBox<>();
        for (Sejour s : sejours) {
            sejourComboBox.addItem(s);
        }

        JTextField produitField = new JTextField();
        JTextField prixField = new JTextField();

        dialog.add(new JLabel("Séjour :"));
        dialog.add(sejourComboBox);
        dialog.add(new JLabel("Produit:"));
        dialog.add(produitField);
        dialog.add(new JLabel("Prix (€):"));
        dialog.add(prixField);

        JButton saveBtn = new JButton("Enregistrer");
        JButton cancelBtn = new JButton("Annuler");

        saveBtn.addActionListener(e -> {
            try {
                Sejour selectedSejour = (Sejour) sejourComboBox.getSelectedItem();
                if (selectedSejour == null) {
                    JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner un séjour.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int sejourId = selectedSejour.getId();
                String produit = produitField.getText();
                double prix = Double.parseDouble(prixField.getText());

                if (produit.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Le produit ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ConsommationController.createConsommation(sejourId, produit, prix);
                loadConsommations(sejourId);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.add(saveBtn);
        dialog.add(cancelBtn);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
