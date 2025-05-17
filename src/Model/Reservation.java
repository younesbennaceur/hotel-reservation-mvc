package src.Model;

import java.time.LocalDate;
import java.util.List;

public class Reservation {
    private int id;
    private int clientId;
    private List<Integer> chambresId; // plusieurs chambres
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean annulee;

    public Reservation(int id, int clientId, List<Integer> chambresId, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.clientId = clientId;
        this.chambresId = chambresId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.annulee = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public List<Integer> getChambresId() {
        return chambresId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    // Setters
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public void annuler() {
        this.annulee = true;
    }

}
