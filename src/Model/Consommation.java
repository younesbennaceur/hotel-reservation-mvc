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
    }}
    