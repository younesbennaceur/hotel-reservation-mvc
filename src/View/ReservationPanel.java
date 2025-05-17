// ReservationPanel.java

package src.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import src.View.outil.ButtonColumn;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ReservationPanel extends JPanel {

    private JTextField searchReservationField;
    private JButton searchReservationButton;
    private JButton addReservationButton;
    private JTable reservationTable;

    public ReservationPanel() {
        setLayout(new BorderLayout());

        // --- Composants de recherche et ajout ---
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

        // --- Tableau des réservations ---
        String[] columnNames = {"Client", "Chambre", "Date", "Modifier", "Supprimer"};
        Object[][] data = {
            {"John Doe", "101", "2024-06-10", "Modifier", "Supprimer"},
            {"Jane Smith", "102", "2024-06-12", "Modifier", "Supprimer"}
        };

        reservationTable = new JTable(new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 3; // Seuls les boutons Modifier/Supprimer sont éditables
            }
        });

        new ButtonColumn(reservationTable, new AbstractAction("Modifier") {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                JOptionPane.showMessageDialog(null, "Modifier réservation ligne: " + row);
            }
        }, 3);

        new ButtonColumn(reservationTable, new AbstractAction("Supprimer") {
            public void actionPerformed(ActionEvent e) {
                int row = Integer.parseInt(e.getActionCommand());
                int confirm = JOptionPane.showConfirmDialog(null, "Supprimer réservation ligne: " + row + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) reservationTable.getModel()).removeRow(row);
                }
            }
        }, 4);

        add(new JScrollPane(reservationTable), BorderLayout.CENTER);
    }

    // Accesseurs si nécessaires (optionnel)
    public JButton getAddReservationButton() {
        return addReservationButton;
    }

    public JTextField getSearchReservationField() {
        return searchReservationField;
    }

    public JButton getSearchReservationButton() {
        return searchReservationButton;
    }

    public JTable getReservationTable() {
        return reservationTable;
    }
}
