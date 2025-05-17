package src.Model;

public class Consommation {
    private int id;
    private int sejourId;
    private String type;
    private double montant;

    public Consommation(int id, int sejourId, String type, double montant) {
        this.id = id;
        this.sejourId = sejourId;
        this.type = type;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public int getSejourId() {
        return sejourId;
    }

    public String getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSejourId(int sejourId) {
        this.sejourId = sejourId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}


// when you try to get a chambre 
// check if the chambre is not reserved
    // to do that you need to check if the chambre is not reserved in the date range
    // the date range is given by the user
    // so i'll get the date range, the chambre id
    // look through all the reservations with the same chambre id
// if the reservation is not in the date range, then the chambre is available

// things to add to the model:
    // 1. date range in the reservation :check:

// the controllers that i need :
    // 1. get reservation by chambre id
    // 2. get reservation by date range
    // 3. get reservation by chambre id and date range



    // 4. create reservation :
              // 1. get the date range and chmabre id
              // 3. use the get reservation by chambre id and date range
              // 4. if the reservation is not in the date range, then the chambre is available
              // 5. create the reservation

