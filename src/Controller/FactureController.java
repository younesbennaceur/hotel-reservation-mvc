package src.Controller;

import java.time.temporal.ChronoUnit;
import java.util.List;
import src.config.*;
import src.Model.Client;
import src.Model.Hotel;
import src.Model.Reservation;

import src.Model.Chambre;
import src.Model.Consommation;
import src.Model.Facture;
import src.Model.Sejour;

public class  FactureController {
    public FactureController() {
    }
  public static Facture createFacture(int sejourId) {
    Sejour sejour = SejourController.getSejour(sejourId);
    if (sejour == null) {
        System.out.println("Séjour introuvable.");
        return null;
    }

    Reservation reservation = db.getInstance().reservations.get(sejour.getReservationId());
    if (reservation == null) {
        System.out.println("Réservation introuvable.");
        return null;
    }

    // ✅ Calculer le nombre de jours
    long nbJours = ChronoUnit.DAYS.between(sejour.getDateDebut(), reservation.getDateFin());

    // ✅ Total chambres : uniquement celles de la réservation
    double totalChambres = 0;
    for (int chambreId : reservation.getChambresId()) {
        Chambre chambre = db.getInstance().chambres.get(chambreId);
        if (chambre != null) {
            totalChambres += chambre.getPrix() * nbJours;
        }
    }

    // ✅ Total consommations
    double totalConsommations = 0;
    for (Consommation c : db.getInstance().consommations) {
        if (c.getSejourId() == sejourId) {
            totalConsommations += c.getMontant();
        }
    }

    // ✅ Créer la facture
    int id = db.getInstance().factures.size();
    double montantTotal = totalChambres + totalConsommations;
    Facture facture = new Facture(id, sejourId, totalChambres, totalConsommations, montantTotal);
    db.getInstance().factures.add(facture);

    System.out.println("Facture créée pour le séjour ID " + sejourId + ", ID facture: " + id);
    return facture;
}

    public static String toString(Facture facture) {
        return "Facture ID: " + facture.getId() +
               ", Séjour ID: " + facture.getSejourId() +
               ", Total Chambres: " + facture.getTotalChambres() +
               ", Total Consommations: " + facture.getTotalConsommations() +
               ", Montant Total: " + facture.getMontantTotal();
    }

}