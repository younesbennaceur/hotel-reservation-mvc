package src.Controller;

import src.Model.Sejour;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import src.Config.db;
import src.Model.Client;
import src.Model.Consommation;
import src.Model.Reservation;
import src.Model.Chambre;

public class SejourController {
    // Créer un séjour
    public static Sejour createSejour(int reservationId) {
        Reservation reservation = db.getInstance().reservations.get(reservationId);
        if (reservation == null) {
            System.out.println("Réservation introuvable.");
            return null;
        }
        int id = db.getInstance().sejours.size();
        LocalDate dateDebut = reservation.getDateDebut();
        LocalDate dateFin = null;

        Sejour sejour = new Sejour(id, reservationId, dateDebut, dateFin);

        db.getInstance().sejours.add(sejour);

        System.out.println("Séjour créé avec ID : " + sejour.getId() +
                " | Réservation ID : " + reservationId +
                " | Date de début : " + dateDebut);
        return sejour;

    }

    // Obtenir un séjour par son ID
    public static Sejour getSejour(int id) {
        if (id < 0 || id >= db.getInstance().sejours.size() || db.getInstance().sejours.get(id) == null) {
            System.out.println("Séjour introuvable.");
            return null;
        }else {
            System.out.println("Séjour trouvé avec ID : " + id);
        }
        return db.getInstance().sejours.get(id);
    }

    public static Sejour getSejourByReservationId(int reservationId) {
        for (Sejour sejour : db.getInstance().sejours) {
            if (sejour.getReservationId() == reservationId) {
                System.out.println("Séjour trouvé avec ID de réservation : " + reservationId);
                System.out.println("Séjour ID : " + sejour.getId());
                return sejour;
            }
           
        }
        return null;
    }

    public static void terminerSejour(int id) {
        Sejour sejour = getSejour(id);
        if (sejour != null) {
            LocalDate dateFin = LocalDate.now();
            sejour.setDateFin(dateFin);
            
            supprimerSejour(id);
            System.out.println("Séjour terminé avec succès.");
            System.out.println("Date de fin : " + dateFin);
        } else {
            System.out.println("Séjour introuvable.");
        }
    }

    public static void supprimerSejour(int id) {
        if (id < 0 || id >= db.getInstance().sejours.size()) {
            System.out.println("Séjour introuvable.");
            return;
        }
        db.getInstance().sejours.set(id, null);
        System.out.println("Séjour supprimé avec succès.");

    }

    // Afficher tous les séjours
    public static void afficherTousLesSejours() {
        List<Sejour> sejours = db.getInstance().sejours;
        if (sejours.isEmpty()) {
            System.out.println("Aucun séjour enregistré.");
        } else {
            for (Sejour s : sejours) {
                System.out.println("ID : " + s.getId() + " | Réservation : " + s.getReservationId() +
                        " | Début : " + s.getDateDebut() +
                        " | Fin : " + (s.getDateFin() != null ? s.getDateFin() : "En cours"));
            }
        }
    }

    public static void sejourner(int reservationId) {
        Reservation reservation = db.getInstance().reservations.get(reservationId);
        if (reservation == null) {
            System.out.println("Réservation introuvable.");
            return;
        }
        Sejour sejour = createSejour(reservationId);
        if (sejour != null) {
            System.out.println("Séjour créé avec succès.");
        } else {
            System.out.println("Erreur lors de la création du séjour.");
        }
    }

    public static double genererFacture(int sejourId) {
    return 000;
    }
}


