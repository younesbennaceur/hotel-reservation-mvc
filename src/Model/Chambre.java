package src.Model;

public class Chambre {
    private int id;
    private int hotelId;
    private int numero;
    private TypeChambre type;
    private double prix;
    private boolean disponible;

    public enum TypeChambre {
        SIMPLE,
        DOUBLE,
        SUITE
    }

    public Chambre(int id, int hotelId, int numero, TypeChambre type, double prix) {
        this.id = id;
        this.hotelId = hotelId;
        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.disponible = true;
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

    public boolean isDisponible() {
        return disponible;
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

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
