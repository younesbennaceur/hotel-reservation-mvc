package src.Model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
  private int id;
  private String nom;
  private String adresse;

  public Hotel(int id, String nom, String adresse) {
    this.id = id;
    this.nom = nom;
    this.adresse = adresse;

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