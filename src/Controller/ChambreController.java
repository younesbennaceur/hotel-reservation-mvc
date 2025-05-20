package src.Controller;

import src.Model.Chambre;
import src.Model.Reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import src.config.*;

public class ChambreController {

    public ChambreController() {
    }

    public static Chambre createChambre(int hotelId, int numero, Chambre.TypeChambre type, Double prix) {
        int id = db.getInstance().chambres.size();
        Chambre chambre = new Chambre(id, hotelId, numero, type, prix);
        db.getInstance().chambres.add(chambre);
        return db.getInstance().chambres.get(id);
    }

    public static Chambre getChambre(int id) {
        List<Chambre> chambres = db.getInstance().chambres;

        if (id < 0 || id >= chambres.size() || db.getInstance().chambres.get(id) == null) {
            System.out.println("Chambre ID " + id + " not found.");
            return null;
        }

        return chambres.get(id);
    }

    public static List<Chambre> getChambresByHotelId(int hotelId) {
    List<Chambre> allChambres = db.getInstance().chambres;
    List<Chambre> result = new ArrayList<>();

    for (Chambre chambre : allChambres) {
        if (chambre != null && chambre.getHotelId() == hotelId) {
            result.add(chambre);
        }
    }

    return result;
}

    public static Chambre updateChambre(int id, int numero, Chambre.TypeChambre type, Double prix) {
        if (id < 0 || id >= db.getInstance().chambres.size() || db.getInstance().chambres.get(id) == null) {
            System.out.println("Chambre ID " + id + " not found.");
            return null;
        }
        Chambre chambre = db.getInstance().chambres.get(id);
        chambre.setNumbero(numero);
        chambre.setType(type);
        chambre.setPrix(prix);
        
        return chambre;
    }

    public static String deleteChambre(int id) {
        if (id < 0 || id >= db.getInstance().chambres.size() || db.getInstance().chambres.get(id) == null) {
            return "Chambre ID " + id + " not found.";
        }
        Chambre chambre = db.getInstance().chambres.set(id, null);
        return "Chambre id: " + chambre.getId() + " deleted succefully";
    }
    public static List<Chambre> getAllChambres() {
        List<Chambre> chambres = new ArrayList<>();
        for (Chambre chambre : db.getInstance().chambres) {
            if (chambre != null) {
                chambres.add(chambre);
            }
        }
        return chambres;
    }
    public static List<Chambre> getChambresDisponibles(LocalDate dateDebut, LocalDate dateFin) {
        List<Chambre> chambresDisponibles = new ArrayList<>();

        for (Chambre chambre : db.getInstance().chambres) {
            if (chambre == null) continue;

            boolean estDisponible = true;

            for (Reservation reservation : db.getInstance().reservations) {
                if (reservation == null) continue;

                if (reservation.getChambresId().contains(chambre.getId())) {
                    // Test de chevauchement des dates
                    boolean chevauchement = reservation.getDateDebut().isBefore(dateFin)
                            && reservation.getDateFin().isAfter(dateDebut);

                    if (chevauchement) {
                        estDisponible = false;
                        break;
                    }
                }
            }

            if (estDisponible) {
                chambresDisponibles.add(chambre);
            }
        }
        if (chambresDisponibles.isEmpty()) {
            System.out.println("Aucune chambre disponible pour la période demandée.");
        } else {
            System.out.println("Chambres disponibles :");
            for (Chambre chambre : chambresDisponibles) {
                System.out.println("Chambre ID : " + chambre.getId() +
                        " | Type : " + chambre.getType() +
                        " | Prix : " + chambre.getPrix() + "€");
            }
        }

         

        return chambresDisponibles;
    }
}