package dat.config;

import dat.entities.Animal;
import dat.entities.Zoo;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("your-persistence-unit-name");

        List<Animal> zoo1Animals = getZoo1Animals().stream().collect(Collectors.toList());
        List<Animal> zoo2Animals = getZoo2Animals().stream().collect(Collectors.toList());

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Zoo zoo1 = new Zoo("Zoo 1", "Location 1", zoo1Animals);
            Zoo zoo2 = new Zoo("Zoo 2", "Location 2", zoo2Animals);
            em.persist(zoo1);
            em.persist(zoo2);
            em.getTransaction().commit();
        }
    }

    @NotNull
    private static Set<Animal> getZoo1Animals() {
        Animal a1 = new Animal(1, 101, null, "Lion", 5);
        Animal a2 = new Animal(2, 102, null, "Tiger", 3);
        Animal a3 = new Animal(3, 103, null, "Elephant", 10);

        Animal[] animalArray = {a1, a2, a3};
        return Set.of(animalArray);
    }

    @NotNull
    private static Set<Animal> getZoo2Animals() {
        Animal a4 = new Animal(4, 104, null, "Giraffe", 7);
        Animal a5 = new Animal(5, 105, null, "Zebra", 4);
        Animal a6 = new Animal(6, 106, null, "Monkey", 2);

        Animal[] animalArray = {a4, a5, a6};
        return Set.of(animalArray);
    }
}