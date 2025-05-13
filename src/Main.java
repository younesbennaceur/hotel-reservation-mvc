package src;

import java.util.List;

import src.Model.Hotel;
import src.Model.Chambre;
import src.Model.Client;

import src.Controller.HotelController;
import src.Controller.ChambreController;
import src.Controller.ClientController;

public class Main {
    public static void main(String[] args) {

        // Gestion des Hotel
        Hotel newHotel = HotelController.createHotel("Hotel de Paris", "123 Rue de Paris");
        System.out.println("First Hotel" + newHotel.getNom() + " " + newHotel.getAdresse());

        HotelController.updateHotel(0, "hotel lyon", "123 Rue de Lyon");
        System.out.println("Updated Hotel" + newHotel.getNom() + " " + newHotel.getAdresse());

        // String msg = HotelController.deleteHotel(0);
        // System.out.println(msg);

        HotelController.getHotel(0);

        // Gestion des Chambres
        Chambre newChambre = ChambreController.createChambre(0, 10, Chambre.TypeChambre.DOUBLE, 500);
        System.out.println("First Chambre " + newChambre.getNumero() + " " + newChambre.getPrix());
        Chambre newChambre2 = ChambreController.createChambre(2, 20, Chambre.TypeChambre.DOUBLE, 500);
        System.out.println("Second Chambre " + newChambre2.getNumero() + " " + newChambre2.getPrix());

        List<Chambre> chambreByHotel = ChambreController.getChambresByHotelId(0);
        for (Chambre chambre : chambreByHotel) {
            System.out.println(chambre.getNumero());
        }

        ChambreController.updateChambre(0, 10, Chambre.TypeChambre.DOUBLE, 500, false);
        System.out.println("Updated Chambre " + newChambre.getNumero() + " " + newChambre.getPrix());

        String msg = ChambreController.deleteChambre(0);
        System.out.println(msg);

        ChambreController.getChambre(0);

        // Gestion des Client
        ClientController.createClient(0, "Younes", "BENNACEUR", "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
        System.out.println("First Client " + ClientController.getClient(0).getNom() + " "
                + ClientController.getClient(0).getPrenom());
        ClientController.updateClient(0, "Younes", "Salmi", "salmisifo@gmail.com", "0651234567");
        System.out.println("Updated Client " + ClientController.getClient(0).getNom() + " "
                + ClientController.getClient(0).getPrenom());
        String msg2 = ClientController.deleteClient(0);
        System.out.println(msg2);
        ClientController.getClient(0);
        ClientController.createClient(0, "Sifo", "BENNACEUR", "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
        ClientController.createClient(0, "Younes", "BENNACEUR", "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
        List<Client> clientByHotel = ClientController.getClientsByHotelId(0);
        for (Client client : clientByHotel) {
            System.out.println(client.getNom());
        }

        // Gestion des Reservation

    }
}