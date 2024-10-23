package dat.config;

import dat.entities.Animal;
import dat.entities.Species;
import dat.entities.Zoo;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Populate {
    /*
    private static List<Animal> zoo1Animals = getZoo1Animals().stream().collect(Collectors.toList());
    private static List<Animal> zoo2Animals = getZoo2Animals().stream().collect(Collectors.toList());

    private static Zoo zoo1 = new Zoo("Zoo 1", "Location 1");
    private static Zoo zoo2 = new Zoo("Zoo 2", "Location 2");
   private static Species lion = new Species("Lion", "Carnivore", "Savannah");
   private static Species tiger = new Species("Tiger", "Carnivore", "Forest");
   private static Species elephant = new Species("Elephant", "Herbivore", "Savannah");
   private static Species giraffe = new Species("Giraffe", "Herbivore", "Savannah");
   private static Species zebra = new Species("Zebra", "Herbivore", "Savannah");

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("animal");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // persist zoos
            em.persist(zoo1);
            em.persist(zoo2);
            // persist species
            em.persist(lion);
            em.persist(tiger);
            em.persist(elephant);
            em.persist(giraffe);
            em.persist(zebra);


            zoo1.setAnimals(zoo1Animals);
            zoo2.setAnimals(zoo2Animals);

            zoo1Animals.forEach(em::persist);
            zoo2Animals.forEach(em::persist);

            em.getTransaction().commit();
        }
    }

    @NotNull
    private static Set<Animal> getZoo1Animals() {


        Animal a1 = new Animal(lion.getId(), zoo1, "Simba", 5);
        Animal a2 = new Animal(tiger.getId(), zoo1, "Shere Khan", 3);
        Animal a3 = new Animal(elephant.getId(), zoo1, "Dumbo", 10);
        Animal a4 = new Animal(giraffe.getId(), zoo1, "Melman", 7);
        Animal a5 = new Animal(zebra.getId(), zoo1, "Marty", 4);

        Animal[] animalArray = {a1, a2, a3, a4, a5};
        return Set.of(animalArray);
    }

    @NotNull
    private static Set<Animal> getZoo2Animals() {

        Animal a6 = new Animal(lion.getId(), zoo2, "Nala", 7);
        Animal a7 = new Animal(tiger.getId(), zoo2, "Rajah", 4);
        Animal a8 = new Animal(elephant.getId(), zoo2, "Babar", 2);
        Animal a9 = new Animal(giraffe.getId(), zoo2, "Geoffrey", 6);
        Animal a10 = new Animal(zebra.getId(), zoo2, "Stripes", 3);

        Animal[] animalArray = {a6, a7, a8, a9, a10};
        return Set.of(animalArray);
    }
    */

}