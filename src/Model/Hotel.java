package src.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
    
public class Hotel {
    private String nom;
    private String adresse;
    private List<Chambre> chambres;
    private List<Reservation> reservations;
    private List<Client> clients;


      // Constructeur de l'hôtel
      public Hotel(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        this.chambres = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    // ajouter une chambre à l'hôtel
     public void AjouterChambre(Chambre chambre){
        chambres.add(chambre);
    }

    // ajouter une réservation à l'hôtel

     public void AjouterResevation(Reservation reservation){
        reservations.add(reservation); 
    }

      // Ajouter un client
     public void enregistrerClient(Client client) {
        clients.add(client);
    }

     // Retourner la liste des chambres disponibles

     
   
      // Getter pour les clients et les réservations et les chambres
      public List<Client> getClients() {
        return new ArrayList<>(clients);
    }

      public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
      public List<Chambre> getChambres() {
        return new ArrayList<>(chambres);
      }

    // Getter pour le nom et l'adresse de l'hôtel  
    public String getNom() {
        return nom;
    }
    public String getAdresse() {
        return adresse;
    }




}
 

    
 

   
