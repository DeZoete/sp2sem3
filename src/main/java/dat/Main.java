package dat;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populate;
import jakarta.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("animal");
       //Populate.Poulate();
        ApplicationConfig.startServer(7070);
        //comment
    }
}