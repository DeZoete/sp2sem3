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
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("your-persistence-unit-name");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create Species
            Species lion = new Species("Lion", "Carnivore", "Savannah");
            Species tiger = new Species("Tiger", "Carnivore", "Forest");
            Species elephant = new Species("Elephant", "Herbivore", "Savannah");
            em.persist(lion);
            em.persist(tiger);
            em.persist(elephant);

            // Create Zoos
            Zoo zoo1 = new Zoo("Zoo 1", "Location 1", null);
            Zoo zoo2 = new Zoo("Zoo 2", "Location 2", null);
            em.persist(zoo1);
            em.persist(zoo2);

            // Create Animals and link them to Species and Zoos
            List<Animal> zoo1Animals = getZoo1Animals(zoo1, lion, tiger, elephant).stream().collect(Collectors.toList());
            List<Animal> zoo2Animals = getZoo2Animals(zoo2, lion, tiger, elephant).stream().collect(Collectors.toList());

            zoo1.setAnimals(zoo1Animals);
            zoo2.setAnimals(zoo2Animals);

            zoo1Animals.forEach(em::persist);
            zoo2Animals.forEach(em::persist);

            em.getTransaction().commit();
        }
    }

    @NotNull
    private static Set<Animal> getZoo1Animals(Zoo zoo, Species lion, Species tiger, Species elephant) {
        Animal a1 = new Animal(1, lion, zoo, "Lion 1", 5);
        Animal a2 = new Animal(2, tiger, zoo, "Tiger 1", 3);
        Animal a3 = new Animal(3, elephant, zoo, "Elephant 1", 10);

        Animal[] animalArray = {a1, a2, a3};
        return Set.of(animalArray);
    }

    @NotNull
    private static Set<Animal> getZoo2Animals(Zoo zoo, Species lion, Species tiger, Species elephant) {
        Animal a4 = new Animal(4, lion, zoo, "Lion 2", 7);
        Animal a5 = new Animal(5, tiger, zoo, "Tiger 2", 4);
        Animal a6 = new Animal(6, elephant, zoo, "Elephant 2", 2);

        Animal[] animalArray = {a4, a5, a6};
        return Set.of(animalArray);
    }
}