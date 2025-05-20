

package src.Controller;

import src.Model.Hotel;
import src.Model.Client;
import src.config.*;

import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private Hotel hotel; // Référence à l'hôtel

    // Constructeur
    public ClientController(Hotel hotel) {
        this.hotel = hotel;
    }

    // Méthode pour créer un client
    public static Client createClient(int hotelId, String nom, String prenom, String email, String telephone) {
        int id = db.getInstance().clients.size(); // ID du client
        Client client = new Client(id, hotelId, nom, prenom, email, telephone); // Création du client
        db.getInstance().clients.add(client); // Ajout du client à la liste des clients
        return db.getInstance().clients.get(id); // Retourne le client créé
    }

    // Méthode pour obtenir un client par son ID
    public static Client getClient(int id) {
        if (id < 0 || id >= db.getInstance().clients.size() || db.getInstance().clients.get(id) == null) {
            System.out.println("Client ID " + id + " not found.");
            return null; // Retourne null si l'ID est invalide
        }
        return db.getInstance().clients.get(id);
    }

    public static List<Client> getClientsByHotelId(int hotelId) {
        List<Client> allClients = db.getInstance().clients;
        List<Client> result = new ArrayList<>();

        for (Client Client : allClients) {
           
            if (Client != null && Client.getHotelId() == hotelId) {
            result.add(Client);
        }
        }

        return result;
    }

    // Méthode pour mettre à jour un client
    public static Client updateClient(int id, String nom, String prenom, String email, String telephone) {
        if (id < 0 || id >= db.getInstance().clients.size() || db.getInstance().clients.get(id) == null) {
            System.out.println("Client ID " + id + " not found.");
            return null; // Retourne null si l'ID est invalide
        }
        Client client = db.getInstance().clients.get(id);
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setEmail(email);
        client.setTelephone(telephone);
        return client;
    }

    public static String deleteClient(int id) {
        if (id < 0 || id >= db.getInstance().clients.size() || db.getInstance().clients.get(id) == null) {
            return "Client ID " + id + " not found."; // Retourne un message d'erreur si l'ID est invalide
        }
        Client client = db.getInstance().clients.set(id, null);
        return "Clint id: " + client.getId() + " deleted successfully";
    }
    // Méthode pour obtenir tous les clients
    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        for (Client client : db.getInstance().clients) {
            if (client != null) {
                clients.add(client);
            }
        }
        return clients;
    }

}