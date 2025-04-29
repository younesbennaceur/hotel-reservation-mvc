package src.Model;
import java.util.Date;
import java.util.List;

public class Reservation {
    private Date debut;
    private Date fin;
    private List<Chambre> chambres; 
    private Client client;  // Client lié à la réservation
    private boolean annulee;

     // Constructeur
     public Reservation(Date debut, Date fin, Client client, List<Chambre> chambres) {
        this.debut = debut;
        this.fin = fin;
        this.client = client;
        this.chambres = chambres;
        this.annulee = false; // Par défaut la réservation n'est pas annulée
    }

        // Getters et setters
    public Date getDateDebut() { return debut; }
    public Date getDateFin() { return fin; }
    public Client getClient() { return client; }
    public List<Chambre> getChambres() { return chambres; }
    public boolean isAnnulee() { return annulee; }

    // Annuler une réservation
    public void annuler() {
        annulee = true;
    }

    }
    

