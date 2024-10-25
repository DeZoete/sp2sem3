package dat.config;

import dat.entities.Animal;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Populate {

    public static void Poulate() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("animal");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create species
            Species lion = new Species("Lion", "Carnivore", "Savannah");
            Species tiger = new Species("Tiger", "Carnivore", "Forest");
            Species elephant = new Species("Elephant", "Herbivore", "Savannah");
            Species giraffe = new Species("Giraffe", "Herbivore", "Savannah");
            Species zebra = new Species("Zebra", "Herbivore", "Savannah");

            // Create zoos and animals
            Set<Animal> zoo1Animals = getZoo1Animals();
            Set<Animal> zoo2Animals = getZoo2Animals();

            Zoo zoo1 = new Zoo("Odense Zoo", "Fyn et elle andet sted");
            Zoo zoo2 = new Zoo("Zoologisk have Lyngby", "Lyngby Storcenter");
            zoo1.setAnimals(zoo1Animals);
            zoo2.setAnimals(zoo2Animals);

            // Persist zoos
            em.persist(zoo1);
            em.persist(zoo2);

            // Persist species
            em.persist(lion);
            em.persist(tiger);
            em.persist(elephant);
            em.persist(giraffe);
            em.persist(zebra);

            em.getTransaction().commit();
        }
    }

    @NotNull
    private static Set<Animal> getZoo1Animals() {
        // Set species for each animal
        Animal a1 = new Animal("Simba", 5, 1);

        Animal a2 = new Animal("Shere Khan", 3, 2);

        Animal a3 = new Animal("Dumbo", 10, 3);

        Animal a4 = new Animal("Melman", 7, 4);

        Animal a5 = new Animal("Marty", 4, 5);

        Animal a6 = new Animal("Nala", 7, 1);


        return Set.of(a1, a2, a3, a4, a5, a6);
    }

    @NotNull
    private static Set<Animal> getZoo2Animals() {
        // Set species for each animal
        Animal a7 = new Animal("Rajah", 4, 2);

        Animal a8 = new Animal("Babar", 2,3);

        Animal a9 = new Animal("Geoffrey", 6,4);

        Animal a10 = new Animal("Stripes", 3,5);

        return Set.of(a7, a8, a9, a10);
    }
}
