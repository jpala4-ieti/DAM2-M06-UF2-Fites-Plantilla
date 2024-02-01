package com.project.fites;

import java.util.Collection;

public class Main {

   public static void main(String[] args) {
      
      Manager.createSessionFactory("fites/hibernate.properties", "fites/hibernate.cfg.xml");

      // Crear ciutats
      Ciutat ciutat1 = Manager.addCiutat("Barcelona");
      Ciutat ciutat2 = Manager.addCiutat("Madrid");

      // Crear aeroports per a Barcelona
      Aeroport aeroport1 = Manager.addAeroportToCiutat(ciutat1.getId(), "Aeroport de Barcelona-El Prat");
      Aeroport aeroport2 = Manager.addAeroportToCiutat(ciutat1.getId(), "Aeroport de Girona-Costa Brava");

      // Crear aeroports per a Madrid
      Aeroport aeroport3 = Manager.addAeroportToCiutat(ciutat2.getId(), "Aeroport Adolfo Suárez Madrid-Barajas");
      Aeroport aeroport4 = Manager.addAeroportToCiutat(ciutat2.getId(), "Aeroport de Torrejón");

      // Crear rutes
      Manager.addRuta("R1", aeroport1, aeroport3);
      Manager.addRuta("R2", aeroport1, aeroport4);
      Manager.addRuta("R3", aeroport2, aeroport3);
      Manager.addRuta("R4", aeroport2, aeroport4);
      Manager.addRuta("R5", aeroport3, aeroport1);
      Manager.addRuta("R6", aeroport3, aeroport2);
      Manager.addRuta("R7", aeroport4, aeroport1);
      Manager.addRuta("R8", aeroport4, aeroport2);

      System.out.println("Ciutats:");
      Collection<?> llista = Manager.listCollection(Ciutat.class);
      System.out.println(Manager.collectionToString(Ciutat.class, llista));

      System.out.println("Aeroports:");
      llista = Manager.listCollection(Aeroport.class);
      System.out.println(Manager.collectionToString(Aeroport.class, llista));

      System.out.println("Rutes:");
      llista = Manager.listCollection(Ruta.class);
      System.out.println(Manager.collectionToString(Ruta.class, llista));

      Manager.close();
   }
}
