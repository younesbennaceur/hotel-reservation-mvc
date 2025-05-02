package src.Config;

import src.Model.Hotel;
import java.util.ArrayList;
import java.util.List;
import src.Model.Chambre;
import src.Model.Client;
import src.Model.Reservation;

public class db {

    private static final db instance = new db();

    public List<Hotel> hotels = new ArrayList<>();
    public List<Chambre> chambres = new ArrayList<>();
    public List<Reservation> reservations = new ArrayList<>();
    public List<Client> clients = new ArrayList<>();

    private db() {
    }

    public static db getInstance() {
        return instance;
    }

}
