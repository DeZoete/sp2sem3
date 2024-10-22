package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "zoos")
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zoo_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(mappedBy = "zoo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    public Zoo(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addAnimal(Animal animal) {
        if (animal != null) {
            animals.add(animal);
            animal.setZoo(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zoo zoo = (Zoo) o;
        return Objects.equals(name, zoo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
