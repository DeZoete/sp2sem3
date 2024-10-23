package dat;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("animal");
        ApplicationConfig.startServer(7070);
    }
}