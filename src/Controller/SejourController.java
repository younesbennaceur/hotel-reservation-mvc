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
    public static Sejour creerSejour(int reservationId) {
        Reservation reservation = db.getInstance().reservations.get(reservationId);
        if (reservation == null) {
            System.out.println("Réservation introuvable.");
            return null;
        }
        int id = db.getInstance().sejours.size();
        LocalDate dateDebut = reservation.getDateDebut();
        LocalDate dateFin = null;


        Sejour sejour = new Sejour ( id, reservationId, dateDebut, dateFin);

        db.getInstance().sejours.add(sejour);  // Utilisation de add au lieu de put

        System.out.println("Séjour créé avec ID : " + sejour.getId());
        return sejour;

    }
  
    // Obtenir un séjour par son ID
    public static Sejour getSejour(int id) {
        if (id < 0 || id >= db.getInstance().sejours.size()) {
            System.out.println("Séjour introuvable.");
            return null;
        }
        return db.getInstance().sejours.get(id);
    }

    public static Sejour getSejourByReservationId(int reservationId) {
        for (Sejour sejour : db.getInstance().sejours) {
            if (sejour.getReservationId() == reservationId) {
                return sejour;
            }
        }
        return null;
    }

    public static void terminerSejour(int id) {
        Sejour sejour = getSejour(id);
        if(sejour != null) {
            LocalDate dateFin = LocalDate.now();
            sejour.setDateFin(dateFin);
            System.out.println("Séjour terminé avec succès.");
        } else {
            System.out.println("Séjour introuvable.");
        }
    }

    public static void supprimerSejour(int id) {
        if (id < 0 || id >= db.getInstance().sejours.size()) {
            System.out.println("Séjour introuvable.");
            return;
        }
        db.getInstance().sejours.remove(id);
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


    public static void genererFacture(int sejourId) {
        Sejour sejour = getSejour(sejourId);
        if (sejour == null){
            System.out.println("Séjour introuvable.");
            return ;
        }
        int reservationId = sejour.getReservationId();
        Reservation reservation = db.getInstance().reservations.get(reservationId);
         
        if (reservation == null) {
        System.out.println("Réservation introuvable.");
        return;
         }

         int clientId = reservation.getClientId();
            Client client = db.getInstance().clients.get(clientId);
            if (client == null) {
                System.out.println("Client introuvable.");
                return;
            }

            List<Integer> chambresId = reservation.getChambresId();

            System.out.println("\n===== FACTURE =====");
    System.out.println("Client : " + client.getNom());
    System.out.println("Séjour du " + sejour.getDateDebut() + " au " +
        (sejour.getDateFin() != null ? sejour.getDateFin() : "en cours"));

        long nbJours = ChronoUnit.DAYS.between(
        sejour.getDateDebut(),
        sejour.getDateFin() != null ? sejour.getDateFin() : LocalDate.now()
    );

    double totalChambres = 0.0;
    System.out.println("\n--- Détails des chambres ---");
    for (int chambreId : chambresId){
        Chambre chambre = db.getInstance().chambres.get(chambreId);
        if (chambre != null){
            double prixChambre = chambre.getPrix();
            double prix = prixChambre * nbJours;
            totalChambres += prix;
            System.out.println("Chambre " + chambre.getNumero() + " : " +
            chambre.getPrix() + "€ x " + nbJours + " nuits = " + prix + "€");
        }
    }
    double consommationsTotal = ConsommationController.calculerTotalConsommations(sejourId);
    System.out.println("\n--- Détails des consommations ---");
    List<Consommation> consommations = ConsommationController.getConsommationsBySejourId(sejourId);
    for (Consommation consommation : consommations) {
        System.out.println(consommation.getType() + " : " + consommation.getMontant() + "€");
    }
        double total = totalChambres + consommationsTotal;
        System.out.println("\n--- Total ---");
        System.out.println("\nTOTAL À PAYER : " + total + "€");
    System.out.println("============================\n");




}}



