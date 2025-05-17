package src.View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import src.Model.Sejour;

public class HotelManagementView extends JFrame {
    private JButton clientsButton = new JButton("Clients");
    private JButton reservationsButton = new JButton("Reservations");
    private JButton chambresButton = new JButton("Chambres");
    private JButton sejoursButton = new JButton("Sejours");

    private JPanel sidebarPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public HotelManagementView(int hotelId) {
        setTitle("Hotel Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        setupSidebar();
        
        // Card Layout with all panels
        setupCardLayout(hotelId);
        
        add(cardPanel, BorderLayout.CENTER);
    }

    private void setupSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(4, 1));
        sidebarPanel.setPreferredSize(new Dimension(150, getHeight()));
        sidebarPanel.add(clientsButton);
        sidebarPanel.add(reservationsButton);
        sidebarPanel.add(chambresButton);
        sidebarPanel.add(sejoursButton);
        add(sidebarPanel, BorderLayout.WEST);
    }

    private void setupCardLayout(int hotelId) {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize all panels
        cardPanel.add(new ClientPanel(hotelId), "CLIENTS");
        cardPanel.add(new ReservationPanel(), "RESERVATIONS");
        cardPanel.add(new ChambrePanel(hotelId), "CHAMBRES");
        cardPanel.add(new SejourPanel(), "SEJOURS");

        // Button actions
        clientsButton.addActionListener(e -> cardLayout.show(cardPanel, "CLIENTS"));
        reservationsButton.addActionListener(e -> cardLayout.show(cardPanel, "RESERVATIONS"));
        chambresButton.addActionListener(e -> cardLayout.show(cardPanel, "CHAMBRES"));
        sejoursButton.addActionListener(e -> cardLayout.show(cardPanel, "SEJOURS"));
    }
}