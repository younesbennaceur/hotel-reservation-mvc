package src.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import src.Controller.FactureController;
import src.Model.Facture;
import src.Model.Sejour;
import src.config.*;

public class FacturePanel extends JPanel {
    private JTable factureTable;
    private DefaultTableModel tableModel;
    private JButton createFactureButton;

    public FacturePanel() {
        this.setLayout(new BorderLayout());

        setupTopPanel();
        setupTable();
        setupActions();
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        createFactureButton = new JButton("Créer Facture");
        topPanel.add(createFactureButton);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupTable() {
        String[] columns = {"ID Facture", "ID Séjour", "Total Chambres (€)", "Total Consommations (€)", "Montant Total (€)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        factureTable = new JTable(tableModel);
        factureTable.setRowHeight(30);

       
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < columns.length; i++) {
            factureTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(factureTable);
        add(scrollPane, BorderLayout.CENTER);

        
        loadAllFactures();
    }

    private void setupActions() {
        createFactureButton.addActionListener(e -> openCreateFactureDialog());
    }

    private void loadAllFactures() {
        tableModel.setRowCount(0); 

        List<Facture> factures = db.getInstance().factures;
        for (Facture f : factures) {
            tableModel.addRow(new Object[]{
                f.getId(),
                f.getSejourId(),
                String.format("%.2f", f.getTotalChambres()),
                String.format("%.2f", f.getTotalConsommations()),
                String.format("%.2f", f.getMontantTotal())
            });
        }
    }

    private void openCreateFactureDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Créer Facture", true);
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(new JLabel("Séjour :"));
        JComboBox<Sejour> sejourCombo = new JComboBox<>();
        loadSejoursInCombo(sejourCombo);
        sejourCombo.setPreferredSize(new Dimension(200, 25));
        panel.add(sejourCombo);

        dialog.add(panel, BorderLayout.CENTER);

        JButton createButton = new JButton("Créer");
        dialog.add(createButton, BorderLayout.SOUTH);

        createButton.addActionListener(e -> {
            Sejour selectedSejour = (Sejour) sejourCombo.getSelectedItem();
            if (selectedSejour != null) {
                Facture facture = FactureController.createFacture(selectedSejour.getId());
                if (facture != null) {
                    JOptionPane.showMessageDialog(dialog, "Facture créée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    loadAllFactures();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la création de la facture.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner un séjour.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }

    private void loadSejoursInCombo(JComboBox<Sejour> combo) {
        combo.removeAllItems();
        List<Sejour> sejours = db.getInstance().sejours;
        for (Sejour s : sejours) {
            combo.addItem(s);
        }
    }
}
