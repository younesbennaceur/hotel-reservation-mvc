package src.Controller;


import src.config.*;
import src.Model.Client;
import src.Model.Consommation;
import src.Model.Sejour;

import java.util.ArrayList;
import java.util.List;

public class ConsommationController {

    public static void createConsommation(int sejourId, String nomProduit, double prix) {
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
        if (id < 0 || id >= db.getInstance().consommations.size() || db.getInstance().consommations.get(id) == null) {
            System.out.println("Consommation introuvable.");
            return null;
        }

        Consommation consommation = db.getInstance().consommations.get(id);
        System.out.println("Consommation trouvée avec ID : " + consommation.getId());

        System.out.println("Consommation ID : " + consommation.getId() +
                " | Séjour ID : " + consommation.getSejourId() +
                " | Produit : " + consommation.getType() +
                " | Prix : " + consommation.getMontant() + "€");
        return db.getInstance().consommations.get(id);
    }

    public static void supprimerConsommation(int id) {
        if (id < 0 || id >= db.getInstance().consommations.size() || db.getInstance().consommations.get(id) == null) {
            System.out.println("Consommation introuvable.");
            return;
        }
        db.getInstance().consommations.set(id, null);
        System.out.println("Consommation supprimée avec succès.");
    }

    public static List<Consommation> getConsommationsBySejourId(int sejourId) {
        List<Consommation> resultats = new ArrayList<>();
        for (Consommation consommation : db.getInstance().consommations) {
            if (consommation == null)
                continue; // ← skip nulls
            if (consommation.getSejourId() == sejourId) {
                resultats.add(consommation);
                System.out.println("Consommation trouvée pour le séjour ID : " + sejourId);
                System.out.println("Consommation ID : " + consommation.getId() +
                        " | Produit : " + consommation.getType() +
                        " | Prix : " + consommation.getMontant() + "€");
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
        System.out.println("Total des consommations pour le séjour ID " + sejourId + " : " + total + "€");
        return total;
    }

    public static void afficherConsommations(int sejourId) {
        List<Consommation> consommations = getConsommationsBySejourId(sejourId);

        if (consommations == null || consommations.isEmpty()) {
            System.out.println("Aucune consommation trouvée pour le séjour ID : " + sejourId);
            return;
        }

        System.out.println("Consommations pour le séjour ID : " + sejourId);
        for (Consommation c : consommations) {
            if (c == null)
                continue;
            System.out.println("- " + c.getType() + " | Prix : " + c.getMontant() + "€");
        }
    }

}