package src.Model;


public class Chambre {
    private int numero;
    private TypeChambre type; // ici, c'est un enum maintenant
    private double prix;
    private boolean disponible;

    public Chambre(int numero, TypeChambre type, double prix) {
        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.disponible = true; // Par défaut, la chambre est disponible
    }

        // Getters
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
    
        // Setters
        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }
    }

