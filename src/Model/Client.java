package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private int id;
    private int HotelID; // ID de l'hôtel auquel le client est associé
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Client(int id, int HotelID, String nom, String prenom, String email, String telephone) {
        this.id = id;
        this.HotelID = HotelID; // ID de l'hôtel auquel le client est associé

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;

    }

    // Getters
    public int getHotelId() {
        return HotelID;
    } // Retourne l'ID de l'hôtel auquel le client est associé

    public int getId() {
        return id;
    }

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
    public void setId(int id) {
        this.id = id;
    }

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
    @Override
public String toString() {
    return nom + " " + prenom; // Exemple : "John Doe"
}
}