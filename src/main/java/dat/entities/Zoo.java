package dat.entities;

import dat.dtos.ZooDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "zoo")
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zoo_id", nullable = false, unique = true)
    private int zooId;


    @Setter
    @Column(name = "zoo_name", nullable = false, unique = true)
    private String zooName;


    @Setter
    @Column(name = "zoo_location", nullable = false, unique = true)
    private String zooLocation;

    @OneToMany(mappedBy = "zoo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Animal> animals = new HashSet<>();

    public Zoo(String name, String location) {
        this.zooName = name;
        this.zooLocation = location;
    }

    public Zoo(ZooDTO zooDTO) {
        this.zooId = zooDTO.getZooId();
        this.zooName = zooDTO.getZooName();
        this.zooLocation = zooDTO.getZooLocation();
        if (zooDTO.getAnimals() != null) {
            zooDTO.getAnimals().forEach(animalDTO -> animals.add(new Animal(animalDTO)));
        }
    }

    // Bi-directional relationship for all animals in a zoo
    public void setAnimals(Set<Animal> animals) {
        if(animals != null) {
            this.animals = animals;
            for (Animal animal : animals) {
                animal.setZoo(this);
            }
        }
    }

    // Bi-directional relationship
    public void addAnimal(Animal animal) {
        if ( animal != null) {
            this.animals.add(animal);
            animal.setZoo(this);
        }
    }

}