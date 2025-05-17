package src.View;

import javax.swing.*;
import javax.swing.table.*;
import src.View.outil.ButtonColumn;
import src.Controller.ChambreController;
import src.Model.Chambre;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChambrePanel extends JPanel {
    private JTable chambreTable;
    private JTextField searchChambreField;
    private JButton searchChambreButton;
    private JButton addChambreButton;
    private JButton refreshButton;
    private DefaultTableModel tableModel;
    private int currentHotelId;

    public ChambrePanel(int hotelId) {
        this.currentHotelId = hotelId;
        setLayout(new BorderLayout());
        initializeComponents();
        loadChambreData();
    }

    private void initializeComponents() {
        searchChambreField = new JTextField(15);
        searchChambreButton = new JButton("Rechercher");
        addChambreButton = new JButton("Ajouter Chambre");
        refreshButton = new JButton(new ImageIcon("refresh.png"));
        refreshButton.setToolTipText("Actualiser");

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JLabel("Chercher chambre:"));
        centerPanel.add(searchChambreField);
        centerPanel.add(searchChambreButton);
        topPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(refreshButton);
        rightPanel.add(addChambreButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Numéro", "Type", "Prix", "Modifier", "Supprimer"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 4;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0 -> Integer.class;
                    case 3 -> Double.class;
                    default -> Object.class;
                };
            }
        };

        chambreTable = new JTable(tableModel);
        customizeTableAppearance();
        add(new JScrollPane(chambreTable), BorderLayout.CENTER);

        addActionListeners();
    }

    private void customizeTableAppearance() {
        chambreTable.setRowHeight(30);
        chambreTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chambreTable.setAutoCreateRowSorter(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < 4; i++) {
            chambreTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        chambreTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }

    private void addActionListeners() {
        addChambreButton.addActionListener(e -> showAddChambreDialog());
        searchChambreButton.addActionListener(e -> searchChambre());
        refreshButton.addActionListener(e -> loadChambreData());

        Action modifyAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                modifyChambre(row);
            }
        };

        Action deleteAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                deleteChambre(row);
            }
        };

        new ButtonColumn(chambreTable, modifyAction, 4).setText("Modifier");
        new ButtonColumn(chambreTable, deleteAction, 5).setText("Supprimer");
    }

    private void loadChambreData() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                tableModel.setRowCount(0);
                List<Chambre> chambres = ChambreController.getChambresByHotelId(currentHotelId);
                for (Chambre chambre : chambres) {
                    if (chambre != null) {
                        addChambreToTable(chambre);
                    }
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                } catch (InterruptedException | ExecutionException ex) {
                    JOptionPane.showMessageDialog(ChambrePanel.this, "Erreur lors du chargement des chambres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void addChambreToTable(Chambre chambre) {
        tableModel.addRow(new Object[]{
                chambre.getId(),
                chambre.getNumero(),
                chambre.getType().toString(),
                chambre.getPrix()
        });
    }

    private void showAddChambreDialog() {
        JDialog dialog = createChambreDialog("Ajouter une nouvelle chambre", null);
        dialog.setVisible(true);
    }

    private void modifyChambre(int row) {
        int chambreId = (int) tableModel.getValueAt(row, 0);
        Chambre chambre = ChambreController.getChambre(chambreId);
        if (chambre != null) {
            JDialog dialog = createChambreDialog("Modifier chambre", chambre);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chambre non trouvée!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JDialog createChambreDialog(String title, Chambre chambre) {
        JDialog dialog = new JDialog();
        dialog.setTitle(title);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));
        dialog.setModal(true);

        JTextField numeroField = new JTextField();
        JComboBox<Chambre.TypeChambre> typeCombo = new JComboBox<>(Chambre.TypeChambre.values());
        JTextField prixField = new JTextField();

        if (chambre != null) {
            numeroField.setText(String.valueOf(chambre.getNumero()));
            typeCombo.setSelectedItem(chambre.getType());
            prixField.setText(String.valueOf(chambre.getPrix()));
        }

        dialog.add(new JLabel("Numéro:"));
        dialog.add(numeroField);
        dialog.add(new JLabel("Type:"));
        dialog.add(typeCombo);
        dialog.add(new JLabel("Prix:"));
        dialog.add(prixField);

        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");

        saveButton.addActionListener(e -> handleSaveAction(
                chambre != null ? chambre.getId() : -1,
                numeroField.getText(),
                (Chambre.TypeChambre) typeCombo.getSelectedItem(),
                prixField.getText(),
                dialog
        ));
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        return dialog;
    }

    private void handleSaveAction(int id, String numero, Chambre.TypeChambre type,
                                  String prix, JDialog dialog) {
        if (!validateChambreInput(numero, prix)) return;
        try {
            int num = Integer.parseInt(numero);
            double price = Double.parseDouble(prix);

            if (id == -1) {
                ChambreController.createChambre(currentHotelId, num, type, price);
                JOptionPane.showMessageDialog(this, "Chambre ajoutée avec succès!");
            } else {
                ChambreController.updateChambre(id, num, type, price);
                JOptionPane.showMessageDialog(this, "Chambre modifiée avec succès!");
            }

            loadChambreData();
            dialog.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateChambreInput(String numero, String prix) {
        if (numero.isEmpty() || prix.isEmpty()) {
            showError("Tous les champs sont obligatoires!");
            return false;
        }
        try {
            int num = Integer.parseInt(numero);
            double price = Double.parseDouble(prix);
            if (num <= 0 || price <= 0) {
                showError("Numéro et prix doivent être positifs!");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            showError("Veuillez entrer des valeurs numériques valides!");
            return false;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void deleteChambre(int row) {
        int chambreId = (int) tableModel.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer cette chambre?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            String result = ChambreController.deleteChambre(chambreId);
            loadChambreData();
            JOptionPane.showMessageDialog(this, result);
        }
    }

    private void searchChambre() {
        String searchText = searchChambreField.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            loadChambreData();
            return;
        }

        tableModel.setRowCount(0);
        List<Chambre> chambres = ChambreController.getChambresByHotelId(currentHotelId);

        for (Chambre chambre : chambres) {
            if (chambre != null && matchesSearch(chambre, searchText)) {
                addChambreToTable(chambre);
            }
        }
    }

    private boolean matchesSearch(Chambre chambre, String searchText) {
        return String.valueOf(chambre.getNumero()).toLowerCase().contains(searchText) ||
               chambre.getType().toString().toLowerCase().contains(searchText) ||
               String.valueOf(chambre.getPrix()).contains(searchText);
    }
}
