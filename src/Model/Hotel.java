package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
  private int id;
  private String nom;
  private String adresse;
  private List<Chambre> chambres;
  private List<Reservation> reservations;
  private List<Client> clients;


  public Hotel(int id, String nom, String adresse) {
    this.id = id;
    this.nom = nom;
    this.adresse = adresse;
    this.chambres = new ArrayList<>();
    this.reservations = new ArrayList<>();
    this.clients = new ArrayList<>();
  }
  

  public List<Client> getClients() {
    return new ArrayList<>(clients);
  }

  public List<Reservation> getReservations() {
    return new ArrayList<>(reservations);
  }

  public List<Chambre> getChambres() {
    return new ArrayList<>(chambres);
  }
  
  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public String getNom() {
    return nom;
  }

  public String getAdresse() {
    return adresse;
  }

  public int getId() {
    return id;
  }

}
