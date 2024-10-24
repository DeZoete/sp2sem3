package dat.config;

import dat.entities.Animal;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Populate {

    public static void main(String[] args) {
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
            Set<Animal> zoo1Animals = getZoo1Animals(lion, tiger, elephant, giraffe, zebra);
            Set<Animal> zoo2Animals = getZoo2Animals(tiger, elephant, giraffe, zebra);

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
    private static Set<Animal> getZoo1Animals(Species lion, Species tiger, Species elephant, Species giraffe, Species zebra) {
        // Set species for each animal
        Animal a1 = new Animal("Simba", 5);
        a1.setSpecies(lion);
        Animal a2 = new Animal("Shere Khan", 3);
        a2.setSpecies(tiger);
        Animal a3 = new Animal("Dumbo", 10);
        a3.setSpecies(elephant);
        Animal a4 = new Animal("Melman", 7);
        a4.setSpecies(giraffe);
        Animal a5 = new Animal("Marty", 4);
        a5.setSpecies(zebra);
        Animal a6 = new Animal("Nala", 7);
        a6.setSpecies(lion);

        return Set.of(a1, a2, a3, a4, a5, a6);
    }

    @NotNull
    private static Set<Animal> getZoo2Animals(Species tiger, Species elephant, Species giraffe, Species zebra) {
        // Set species for each animal
        Animal a7 = new Animal("Rajah", 4);
        a7.setSpecies(tiger);
        Animal a8 = new Animal("Babar", 2);
        a8.setSpecies(elephant);
        Animal a9 = new Animal("Geoffrey", 6);
        a9.setSpecies(giraffe);
        Animal a10 = new Animal("Stripes", 3);
        a10.setSpecies(zebra);

        return Set.of(a7, a8, a9, a10);
    }
}
