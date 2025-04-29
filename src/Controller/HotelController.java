package src.Controller;
import src.Model.Hotel;
import src.Model.Chambre;   
import src.Model.Client;
import src.Model.Reservation;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import src.Model.TypeChambre;
public class HotelController {
    private Hotel hotel;

     public HotelController(Hotel hotel) { 
        this.hotel = hotel;
     }

    // Méthode pour ajouter une chambre à l'hôtel
    public void ajouterChambre(int numero, TypeChambre type, double prix, boolean disponible) {
        Chambre chambre = new Chambre(numero, type, prix);
        hotel.AjouterChambre(chambre);
    }
    public void ajouterChambre(Chambre chambre) {
        hotel.AjouterChambre(chambre);
    }

      // Ajouter une réservation
      public void ajouterReservation(Client client, List<Chambre> chambres, Date debut, Date fin) {
        Reservation reservation = new Reservation(debut, fin, client, chambres);
        hotel.AjouterResevation(reservation);
    }
    public void ajouterReservation(Reservation reservation) {
        hotel.AjouterResevation(reservation);
    }
    // Ajouter un client
    public void ajouterClient(String nom, String prenom, String email, String telephone) {
        Client client = new Client(nom, prenom, email, telephone);
        hotel.enregistrerClient(client);
        System.out.println("Client ajouté avec succès !");
    }
    public void ajouterClient(Client client) {
        hotel.enregistrerClient(client);
        System.out.println("Client ajouté avec succès !");
    }

    // Chercher des chambres disponibles


    //   // Retourner la liste des clients
    public List<Client> getClients() {
        return hotel.getClients();
    }
    // Retourner la liste des réservations
    public List<Reservation> getReservations() {
        return hotel.getReservations();
    }
    // Retourner la liste des chambres
    public List<Chambre> getChambres() {
        return hotel.getChambres();
    }
    

}
