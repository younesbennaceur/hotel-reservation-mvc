import java.util.ArrayList;
import java.util.List;  
import src.Model.Hotel;
import src.Model.Chambre;
import src.Model.Client;
import src.Model.TypeChambre;
import src.Model.Reservation;
import src.Controller.ReservationController;
import src.Controller.HotelController;
import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Exemple d'utilisation des classes
        Hotel hotel = new Hotel("Hotel de Paris", "Paris");
        
        // Création de chambres
        Chambre chambre1 = new Chambre(101, TypeChambre.SIMPLE, 100.0);
        Chambre chambre2 = new Chambre(102, TypeChambre.DOUBLE, 150.0);
        
        // Ajout des chambres à l'hôtel
        hotel.AjouterChambre(chambre1); 
        hotel.AjouterChambre(chambre2);
                
        // Création d'un client
}
}