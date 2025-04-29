package src.Controller;
import java.util.List;
import src.Model.Client;
import src.Model.Hotel; 
import src.Model.Reservation;
import src.Model.Chambre;
import src.Model.TypeChambre;
import java.util.ArrayList;
import java.util.Date;

public class ClientController {
    private Hotel hotel; // Référence à l'hôtel

    // Constructeur
    public ClientController(Hotel hotel) {
        this.hotel = hotel;
    }

    // Méthode pour ajouter un client
    public void ajouterClient(String nom, String prenom, String email, String telephone) {
        Client client = new Client(nom, prenom, email, telephone);
        hotel.enregistrerClient(client);
        System.out.println("Client ajouté avec succès !");
    }

    // Méthode pour ajouter une réservation
    public void ajouterReservation(Date debut, Date fin, Client client, List<Chambre> chambres) {
        if (!hotel.getClients().contains(client)) {
            System.out.println("Erreur : le client n'est pas enregistré dans l'hôtel.");
            return;
        }
        Reservation reservation = new Reservation(debut, fin, client, chambres);
        client.ajouterReservation(reservation); // Ajouter au client
        hotel.AjouterResevation(reservation); // Ajouter à l'hôtel
        System.out.println("Réservation ajoutée avec succès !");
    }

    // Obtenir la liste des réservations d'un client
    public List<Reservation> getReservations(Client client) {
        return client.getReservations();
    }

    public void annulerReservation(Client client, Reservation reservation) {
        if (client.getReservations().contains(reservation)) {
            reservation.annuler();
            System.out.println("Réservation annulée avec succès !");
        } else {
            System.out.println("Erreur : réservation non trouvée pour ce client.");
        }
    }

     // Récupérer tous les clients
     public List<Client> getClients() {
        return hotel.getClients();
    }
}
