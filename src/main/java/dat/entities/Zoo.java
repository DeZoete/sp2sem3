package dat.entities;

import dat.dtos.AnimalDTO;
import dat.dtos.ZooDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Zoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Setter
    private String name;

    @Column
    @Setter
    private String location;

    @OneToMany(cascade = CascadeType.ALL)
    @Setter
    private List<Animal> animals;

    public Zoo(String name, String location, List<Animal> animals) {
        this.name = name;
        this.location = location;
        this.animals = animals;
    }

    public Zoo(ZooDTO zooDTO) {
        this.id = zooDTO.getId();
        this.name = zooDTO.getName();
        this.location = zooDTO.getLocation();
        if (zooDTO.getAnimals() != null) {
            zooDTO.getAnimals().forEach(animalDTO -> animals.add(new Animal(animalDTO)));
        }
    }
}