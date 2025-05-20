package src.View;

import javax.swing.*;
import java.awt.*;
import src.Controller.ChambreController;
import src.View.FacturePanel;;
;

public class HotelManagementView extends JFrame {
    private JButton clientsButton = new JButton("Clients");
    private JButton reservationsButton = new JButton("Reservations");
    private JButton chambresButton = new JButton("Chambres");
    private JButton sejoursButton = new JButton("Sejours");
    private JButton consommationsButton = new JButton("Consommations");
    private JButton facturesButton = new JButton("Factures"); 

    private JPanel sidebarPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public HotelManagementView(int hotelId) {
        setTitle("Hotel Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        setupSidebar();

       
        setupCardLayout(hotelId);

        add(cardPanel, BorderLayout.CENTER);
    }

    private void setupSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(6, 1)); 
        sidebarPanel.setPreferredSize(new Dimension(150, getHeight()));
        sidebarPanel.add(clientsButton);
        sidebarPanel.add(reservationsButton);
        sidebarPanel.add(chambresButton);
        sidebarPanel.add(sejoursButton);
        sidebarPanel.add(consommationsButton);
        sidebarPanel.add(facturesButton); 
        add(sidebarPanel, BorderLayout.WEST);
    }

    private void setupCardLayout(int hotelId) {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        
        cardPanel.add(new ClientPanel(hotelId), "CLIENTS");
        cardPanel.add(new ReservationPanel(), "RESERVATIONS");
        cardPanel.add(new ChambrePanel(hotelId), "CHAMBRES");
        cardPanel.add(new SejourPanel(), "SEJOURS");
        cardPanel.add(new ConsommationPanel(), "CONSOMMATIONS");
        cardPanel.add(new FacturePanel(), "FACTURES"); 

      
        clientsButton.addActionListener(e -> cardLayout.show(cardPanel, "CLIENTS"));
        reservationsButton.addActionListener(e -> cardLayout.show(cardPanel, "RESERVATIONS"));
        chambresButton.addActionListener(e -> cardLayout.show(cardPanel, "CHAMBRES"));
        sejoursButton.addActionListener(e -> cardLayout.show(cardPanel, "SEJOURS"));
        consommationsButton.addActionListener(e -> cardLayout.show(cardPanel, "CONSOMMATIONS"));
        facturesButton.addActionListener(e -> cardLayout.show(cardPanel, "FACTURES")); 
    }
}
