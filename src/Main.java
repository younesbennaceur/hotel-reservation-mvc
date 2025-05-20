
// File: Main.java
package src;


import javax.swing.SwingUtilities;
import src.View.HotelManagementView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int hotelId = 1; // Example hotel ID
            HotelManagementView view = new HotelManagementView(hotelId);
            view.setVisible(true);
        });
    }
}
