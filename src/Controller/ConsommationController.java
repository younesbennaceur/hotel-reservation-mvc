package src.Controller;

import src.Config.db;

import src.Model.Consommation;
import src.Model.Sejour;

import java.util.ArrayList;
import java.util.List;


public class ConsommationController {

    public static void ajouterConsommation(int sejourId, String nomProduit, double prix) {
    Sejour sejour = SejourController.getSejour(sejourId);
    if (sejour != null) {
        int consommationId = db.getInstance().consommations.size();
        Consommation consommation = new Consommation(consommationId, sejourId, nomProduit, prix);
        db.getInstance().consommations.add(consommation);
        System.out.println("Consommation ajoutée au séjour ID : " + sejourId);
    } else {
        System.out.println("Séjour introuvable.");
    }
    }
    
    public static Consommation getConsommation(int id) {
        if (id < 0 || id >= db.getInstance().consommations.size()) {
            System.out.println("Consommation introuvable.");
            return null;
        }
        return db.getInstance().consommations.get(id);
    }

    public static void supprimerConsommation(int id) {
        if (id < 0 || id >= db.getInstance().consommations.size()) {
            System.out.println("Consommation introuvable.");
            return;
        }
        db.getInstance().consommations.remove(id);
        System.out.println("Consommation supprimée avec succès.");
    }

    public static List<Consommation> getConsommationsBySejourId(int sejourId) {
        List<Consommation> resultats = new ArrayList<>();
        List<Consommation> consommations = db.getInstance().consommations;
        for (Consommation consommation : consommations) {
        if (consommation.getSejourId() == sejourId) {
            resultats.add(consommation);
        }
        }
        return resultats;
    }

    public static double calculerTotalConsommations(int sejourId) {
        double total = 0.0;
        List<Consommation> consommations = getConsommationsBySejourId(sejourId);
        for (Consommation consommation : consommations) {
            total += consommation.getMontant();
        }
        return total;
    }

    public static void afficherConsommations(int sejourId) {
    List<Consommation> consommations = getConsommationsBySejourId(sejourId);

    if (consommations.isEmpty()) {
        System.out.println("Aucune consommation pour ce séjour.");
        return;
    }

    System.out.println("Consommations pour le séjour ID : " + sejourId);
    for (Consommation c : consommations) {
        System.out.println("- " + c.getType() + " | Prix : " + c.getMontant() + "€");
    }
    }

    

}
