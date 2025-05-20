package src.Model;

public class Chambre {
    private int id;
    private int hotelId;
    private int numero;
    private TypeChambre type;
    private double prix;

    public enum TypeChambre {
        SIMPLE,
        DOUBLE,
        SUITE_NORMALE,
        SUITE_PRESIDENTIELLE
    }

    public Chambre(int id, int hotelId, int numero, TypeChambre type, double prix) {
        this.id = id;
        this.hotelId = hotelId;
        this.numero = numero;
        this.type = type;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getNumero() {
        return numero;
    }

    public TypeChambre getType() {
        return type;
    }

    public double getPrix() {
        return prix;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = id;
    }

    public void setNumbero(int numero) {
        this.numero = numero;
    }

    public void setType(TypeChambre typeChambre) {
        this.type = typeChambre;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
@Override
public String toString() {
    return "Chambre " + numero+ " (" + type + ")"; 
}
    
}