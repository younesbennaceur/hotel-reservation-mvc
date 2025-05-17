package src.Controller;

import src.Model.Hotel;

import java.util.List;

import src.Config.db;

public class HotelController {
   private Hotel hotel;

   public HotelController() {
   }

   public static Hotel createHotel(String nom, String adress) {
      int id = db.getInstance().hotels.size();
      Hotel hotel = new Hotel(id, nom, adress);
      db.getInstance().hotels.add(hotel);
      return db.getInstance().hotels.get(id);
   }

   public static Hotel getHotel(int id) {
      List<Hotel> hotels = db.getInstance().hotels;

      if (id < 0 || id >= hotels.size()) {
         System.out.println("Hotel ID " + id + " not found.");
         return null;
      }

      return hotels.get(id);
   }

   public static Hotel updateHotel(int id, String nom, String adresse) {
      if (id < 0 || id >= db.getInstance().hotels.size() || db.getInstance().hotels.get(id) == null) {
         System.out.println("Hotel ID " + id + " not found.");
         return null;
      }
      Hotel hotel = db.getInstance().hotels.get(id);
      hotel.setNom(nom);
      hotel.setAdresse(adresse);
      return hotel;
   }

   public static String deleteHotel(int id) {
      if (id < 0 || id >= db.getInstance().hotels.size() || db.getInstance().hotels.get(id) == null) {
         return "Hotel ID " + id + " not found.";
      }
      Hotel hotel = db.getInstance().hotels.remove(id);
      return "Hotel id: " + hotel.getId() + " deleted succefully";
   }
}
