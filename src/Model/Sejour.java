package src.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sejour {
    private int id;
    private int reservationId;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    public Sejour(int id, int reservationId, LocalDate dateDebut, LocalDate dateFin) {
        this.id = id;
        this.reservationId = reservationId;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
@Override
public String toString() {
    return "Séjour #" + id + " (du " + dateDebut + " au " + (dateFin != null ? dateFin : "en cours") + ")";
}


}