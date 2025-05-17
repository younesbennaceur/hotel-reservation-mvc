package src.View;

import javax.swing.*;
import javax.swing.table.*;

import src.View.outil.ButtonColumn;

import java.awt.*;
import java.awt.event.*;

public class SejourPanel extends JPanel {
    private JTable sejourTable;
    private JTextField searchSejourField;
    private JButton searchSejourButton;
    private JButton addSejourButton;

    public SejourPanel() {
        setLayout(new BorderLayout());

        // Initialize components
        searchSejourField = new JTextField(15);
        searchSejourButton = new JButton("Rechercher");
        addSejourButton = new JButton("Ajouter Séjour");

        // --- Top panel with BorderLayout ---
        JPanel topPanel = new JPanel(new BorderLayout());

        // Center: Search bar
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(new JLabel("Chercher séjour :"));
        centerPanel.add(searchSejourField);
        centerPanel.add(searchSejourButton);
        topPanel.add(centerPanel, BorderLayout.CENTER);

        // Right: Add button
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(addSejourButton);
        topPanel.add(rightPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Table data
        String[] columnNames = {"Client", "Chambre", "Date Début", "Date Fin", "Modifier", "Supprimer"};
        Object[][] data = {
            {"John Doe", "101", "2024-06-01", "2024-06-05", "Modifier", "Supprimer"},
            {"Jane Smith", "102", "2024-06-02", "2024-06-06", "Modifier", "Supprimer"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };

        sejourTable = new JTable(model);

        // Actions for buttons
        Action modifyAction = new AbstractAction("Modifier") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                String clientName = (String) sejourTable.getValueAt(row, 0);
                JOptionPane.showMessageDialog(null, "Modifier le séjour de : " + clientName);
            }
        };

        Action deleteAction = new AbstractAction("Supprimer") {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                String clientName = (String) sejourTable.getValueAt(row, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Supprimer le séjour de : " + clientName + " ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) sejourTable.getModel()).removeRow(row);
                }
            }
        };

        new ButtonColumn(sejourTable, modifyAction, 4); // "Modifier"
        new ButtonColumn(sejourTable, deleteAction, 5); // "Supprimer"

        add(new JScrollPane(sejourTable), BorderLayout.CENTER);
    }
}
