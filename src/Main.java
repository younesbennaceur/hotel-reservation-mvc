package src;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import src.Model.Hotel;
import src.Model.Reservation;
import src.Model.Sejour;
import src.Model.Chambre;
import src.Model.Client;

import src.Controller.HotelController;
import src.Controller.ReservationController;
import src.Controller.ChambreController;
import src.Controller.ClientController;
import src.Controller.ConsommationController;
import src.Controller.SejourController;

public class Main {
    public static void main(String[] args) {

        // Gestion des Hotel
        /*
         
         Hotel newHotel = HotelController.createHotel("Hotel de Paris",
         "123 Rue de Paris");
         System.out.println("First Hotel" + newHotel.getNom() + " " +
         newHotel.getAdresse());
         
         HotelController.updateHotel(0, "hotel lyon", "123 Rue de Lyon");
         System.out.println("Updated Hotel" + newHotel.getNom() + " " +
         newHotel.getAdresse());
         
         // String msg = HotelController.deleteHotel(0);
         // System.out.println(msg);
         
         HotelController.getHotel(0);
         
         // Gestion des Chambres
         Chambre newChambre = ChambreController.createChambre(0, 10,
         Chambre.TypeChambre.DOUBLE, 500);
         System.out.println("First Chambre " + newChambre.getNumero() + " " +
         newChambre.getPrix());
         Chambre newChambre2 = ChambreController.createChambre(2, 20,
         Chambre.TypeChambre.DOUBLE, 500);
         System.out.println("Second Chambre " + newChambre2.getNumero() + " " +
         newChambre2.getPrix());
         
         List<Chambre> chambreByHotel = ChambreController.getChambresByHotelId(0);
         for (Chambre chambre : chambreByHotel) {
         System.out.println(chambre.getNumero());
         }
         
         ChambreController.updateChambre(0, 10, Chambre.TypeChambre.DOUBLE, 500,
         false);
         System.out.println("Updated Chambre " + newChambre.getNumero() + " " +
         newChambre.getPrix());
         
         String msg = ChambreController.deleteChambre(0);
         System.out.println(msg);
         
         ChambreController.getChambre(0);
         
         // Gestion des Client
         ClientController.createClient(0, "Younes", "BENNACEUR",
         "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
         System.out.println("First Client " + ClientController.getClient(0).getNom() +
         " "
         + ClientController.getClient(0).getPrenom());
         ClientController.updateClient(0, "Younes", "Salmi", "salmisifo@gmail.com",
         "0651234567");
         System.out.println("Updated Client " + ClientController.getClient(0).getNom()
         + " "
         + ClientController.getClient(0).getPrenom());
         String msg2 = ClientController.deleteClient(0);
         System.out.println(msg2);
         ClientController.getClient(0);
         ClientController.createClient(0, "Sifo", "BENNACEUR",
         "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
         ClientController.createClient(0, "Younes", "BENNACEUR",
         "yOUNESBENNACEUR2004@GMAIL.COM", "0651234567");
         List<Client> clientByHotel = ClientController.getClientsByHotelId(0);
         for (Client client : clientByHotel) {
         System.out.println(client.getNom());
         }
         */
        // Gestion des sejours
        
         
         HotelController.createHotel("Hotel de Paris", "123 Rue de Paris");
         ClientController.createClient(0, "Younes", "BENNACEUR",
         "younesbennaceur2004@gmail.com", "0651234567");
         ChambreController.createChambre(0, 10, Chambre.TypeChambre.DOUBLE, 500);
         ChambreController.createChambre(0, 20, Chambre.TypeChambre.DOUBLE, 500);
         ChambreController.createChambre(0, 30, Chambre.TypeChambre.DOUBLE, 500);
         LocalDate dateDebut1 = LocalDate.of(2025, 5, 20);
         LocalDate dateFin1 = LocalDate.of(2025, 5, 25);
         LocalDate dateDebut2 = LocalDate.of(2025, 6, 20);
         LocalDate dateFin2 = LocalDate.of(2025, 6, 25);
         List<Integer> chambresId1 = Arrays.asList(0);
         List<Integer> chambresId2 = Arrays.asList(1, 2);
         ReservationController.creatReservation( 0, chambresId2, dateDebut1,
         dateFin1);
         ReservationController.creatReservation( 0, chambresId2, dateDebut2,
         dateFin2);
         
         SejourController.createSejour(0);
         SejourController.createSejour(1);
         SejourController.getSejourByReservationId(0);
         SejourController.getSejourByReservationId(1);
         
         SejourController.afficherTousLesSejours();
         ConsommationController.createConsommation(0, "Jus", 50.0);
         ConsommationController.createConsommation(1, "Vodca", 20.0);
         ConsommationController.createConsommation(1, "RedBul", 50.0);
         ConsommationController.getConsommation(0);
         ConsommationController.getConsommation(1);
         ConsommationController.getConsommationsBySejourId(1);
         ConsommationController.calculerTotalConsommations(1);
         ConsommationController.calculerTotalConsommations(0);
         ConsommationController.afficherConsommations(0);
         ConsommationController.afficherConsommations(1);
        Double montant = SejourController.genererFacture(0);
        System.out.println("Montant total pour le séjour ID 0 : " + montant + "€");
         
         
         
         
         
         
        /*HotelController.createHotel("Hotel de Paris", "123 Rue de Paris");
        ClientController.createClient(0, "Younes", "BENNACEUR", "younes@gmail.com", "0651234567");
        ChambreController.createChambre(0, 10, Chambre.TypeChambre.DOUBLE, 500);
        ChambreController.createChambre(0, 20, Chambre.TypeChambre.DOUBLE, 500);
        ChambreController.createChambre(0, 30, Chambre.TypeChambre.DOUBLE, 500);
        LocalDate dateDebut1 = LocalDate.of(2025, 5, 20);
        LocalDate dateFin1 = LocalDate.of(2025, 6, 25);
        LocalDate dateDebut2 = LocalDate.of(2025, 6, 20);
        LocalDate dateFin2 = LocalDate.of(2025, 6, 25);
        ReservationController.creatReservation(0, Arrays.asList(0, 1), dateDebut1, dateFin1);
        ReservationController.creatReservation(0, Arrays.asList(1, 2), dateDebut2, dateFin2);
         
         
         */
        
         // Gestion des reservations
    }
}