package src.Controller;

import src.Model.Reservation;
import src.Model.Chambre;
import src.Model.Client;
import src.config.db;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ReservationController {
    // create reservation with chambre id and date range
    public static Reservation creatReservation(int clientId, List<Integer> chambresId, LocalDate dateDebut,
            LocalDate dateFin) {
        if (dateDebut.isAfter(dateFin) || dateDebut.equals(dateFin)) {
            System.out.println("Date de début doit être strictement avant la date de fin.");
            return null;
        }

        List<Reservation> reservations = getReservationsByChambreIdAndDateRange(chambresId, dateDebut, dateFin);
        if (!reservations.isEmpty()) {
            System.out.println("La chambre est déjà réservée pour cette période.");
            return null;
        }

        int id = db.getInstance().reservations.size();

        Reservation reservation = new Reservation(id, clientId, chambresId, dateDebut, dateFin);
        db.getInstance().reservations.add(reservation);
        return reservation;
    }

    // get reservation by id
    public static Reservation getReservationById(int id) {
        if (id < 0 || id >= db.getInstance().reservations.size() || db.getInstance().reservations.get(id) == null) {
            System.out.println("Réservation introuvable.");
            return null;
        }
        for (Reservation reservation : db.getInstance().reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

public static String suprimReservation(int id) {
   List<Reservation> reservations = db.getInstance().reservations;
    for (int i = 0; i < reservations.size(); i++) {
        Reservation r = reservations.get(i);
        if (r != null && r.getId() == id) {
            db.getInstance().reservations.set(i, null);
            return "Réservation supprimée avec succès.";
        }
    }
    return "Réservation introuvable.";
}



    // get reservations by chambre id
    public static List<Reservation> getReservationsByChambreId(int chambreId) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : db.getInstance().reservations) {
            if (reservation.getChambresId().contains(chambreId)) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // get reservation by client id
    public static List<Reservation> getReservationsByClientId(int clientId) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : db.getInstance().reservations) {
            if (reservation.getClientId() == clientId) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // get reservations by chambre id and date range
    public static List<Reservation> getReservationsByChambreIdAndDateRange(
            List<Integer> chambresId,
            LocalDate dateDebut,
            LocalDate dateFin) {
        List<Reservation> reservations = new ArrayList<>();
        for (Reservation reservation : db.getInstance().reservations) {
            boolean overlapChambre = !Collections.disjoint(
                    reservation.getChambresId(),
                    chambresId);

            boolean overlapDate = reservation.getDateDebut().isBefore(dateFin) &&
                    reservation.getDateFin().isAfter(dateDebut);

            if (overlapChambre && overlapDate) {
                reservations.add(reservation);
            }
        }
        return reservations;
    }
    public static List<Reservation> getReservationsByHotelId(int hotelId) {
    List<Reservation> allReservations = db.getInstance().reservations;
    List<Reservation> result = new ArrayList<>();

    for (Reservation reservation : allReservations) {
        if (reservation != null ) {
            result.add(reservation);
        }
    }

    return result;
}
   
}