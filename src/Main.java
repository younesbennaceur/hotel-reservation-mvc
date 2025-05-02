package src;

import src.Controller.HotelController;
import src.Model.Hotel;

public class Main {
    public static void main(String[] args) {

        Hotel newHotel = HotelController.createHotel("Hotel de Paris", "123 Rue de Paris");
        System.out.println("First Hotel" + newHotel.getNom() + " " + newHotel.getAdresse());

        HotelController.updateHotel(0, "hotel lyon", "123 Rue de Lyon");
        System.out.println("Updated Hotel" + newHotel.getNom() + " " + newHotel.getAdresse());

        String msg = HotelController.deleteHotel(0);
        System.out.println(msg);
        
        HotelController.getHotel(0);

    }
}