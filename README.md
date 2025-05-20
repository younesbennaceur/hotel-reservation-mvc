# 🏨 Projet Java - Réservation d'Hôtel (MVC)

Projet réalisé en Java dans le cadre du module de Programmation Orientée Objet (POO) par **Younes BENNACEUR** et **Rayane AMBAR**.

## 🎯 Objectif

Créer un système de gestion de réservation d’hôtel en utilisant l’architecture **MVC** et une base de données simulée en mémoire.

## 🧱 Structure

- `models/` : Classes métiers (`Client`, `Chambre`, `Reservation`, etc.)
- `controllers/` : Logique métier avec des méthodes statiques
- `config/db.java` : Fake DB sous forme de singleton contenant toutes les données (List)
- `views/` : Console ou interface Swing
- `Main.java` : Point d’entrée de l'application

## ⚙️ Fonctionnement

- Les **modèles** représentent les entités avec leurs attributs et règles (ex : calcul de facture).
- Les **contrôleurs** valident les données et manipulent les modèles.
- Les **vues** affichent les informations et transmettent les actions de l’utilisateur aux contrôleurs.
- La **fake DB** (`db.java`) stocke toutes les entités et permet un accès simple via `db.getInstance()`.

### Exemple : Création de réservation
```java
ReservationController.creatReservation(
    clientId,
    Arrays.asList(chambreId),
    LocalDate.of(2024, 6, 1),
    LocalDate.of(2024, 6, 5)
);
```
### Lancer le projet
```
javac Main.java
java Main
```
Projet pédagogique — Université Paris-Saclay (L2 Informatique)
