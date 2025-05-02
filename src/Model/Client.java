package src.Model;

import java.util.ArrayList;
import java.util.List;


public class Client {
    private String id; 
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private List<Reservation> reservations; // Liste des réservations
      
    
    public Client(String id, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.reservations = new ArrayList<>(); 
    }

        // Ajouter une réservation à la liste de réservations du client
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }

     // Get réservations
     public List<Reservation> getReservations() {
        return new ArrayList<>(reservations); // Retourne une copie de la liste des réservations
    }

     // Getters
     public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

     // Setters
     public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
