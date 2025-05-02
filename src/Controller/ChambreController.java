package src.Controller;

import src.Model.Chambre;

import java.util.ArrayList;
import java.util.List;

import src.Config.db;

public class ChambreController {

    public ChambreController() {
    }

    public static Chambre createChambre(int hotelId, int numero, Chambre.TypeChambre type, int prix) {
        int id = db.getInstance().chambres.size();
        Chambre chambre = new Chambre(id, hotelId, numero, type, prix);
        db.getInstance().chambres.add(chambre);
        return db.getInstance().chambres.get(id);
    }

    public static Chambre getChambre(int id) {
        List<Chambre> chambres = db.getInstance().chambres;

        if (id < 0 || id >= chambres.size()) {
            System.out.println("Chambre ID " + id + " not found.");
            return null;
        }

        return chambres.get(id);
    }

    public static List<Chambre> getChambresByHotelId(int hotelId) {
        List<Chambre> allChambres = db.getInstance().chambres;
        List<Chambre> result = new ArrayList<>();

        for (Chambre chambre : allChambres) {
            if (chambre.getHotelId() == hotelId) {
                result.add(chambre);
            }
        }

        return result;
    }

    public static Chambre updateChambre(int id, int numero, Chambre.TypeChambre type, int prix, boolean isDisponible) {
        Chambre chambre = db.getInstance().chambres.get(id);
        chambre.setNumbero(numero);
        chambre.setType(type);
        chambre.setPrix(prix);
        chambre.setDisponible(isDisponible);
        return chambre;
    }

    public static String deleteChambre(int id) {
        Chambre chambre = db.getInstance().chambres.remove(id);
        return "Chambre id: " + chambre.getId() + " deleted succefully";
    }
}