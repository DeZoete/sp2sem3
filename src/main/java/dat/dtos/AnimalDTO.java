package dat.dtos;

import dat.entities.Animal;
import dat.entities.Zoo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AnimalDTO {
    private int id;
    private int speciesId;
    private Zoo zoo;
    private String name;
    private int age;

    public AnimalDTO(Animal animal) {
        this.id = animal.getId();
        this.speciesId = animal.getSpeciesId();
        this.zoo = animal.getZoo();
        this.name = animal.getName();
        this.age = animal.getAge();
    }

    public AnimalDTO(int speciesId, Zoo zoo, String name, int age) {
        this.speciesId = speciesId;
        this.zoo = zoo;
        this.name = name;
        this.age = age;
    }

    public static List<AnimalDTO> toAnimalDTOList(List<Animal> animalList) {
        return animalList.stream().map(AnimalDTO::new).collect(Collectors.toList());
    }
}