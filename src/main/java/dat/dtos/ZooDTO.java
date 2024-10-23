package dat.dtos;

import dat.entities.Zoo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ZooDTO {
    private int id;
    private String name;
    private String location;
    private List<AnimalDTO> animals;

    public ZooDTO(Zoo zoo) {
        this.id = zoo.getId();
        this.name = zoo.getName();
        this.location = zoo.getLocation();
        this.animals = zoo.getAnimals().stream().map(AnimalDTO::new).collect(Collectors.toList());
    }

    public ZooDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static List<ZooDTO> toZooDTOList(List<Zoo> zooList) {
        return zooList.stream().map(ZooDTO::new).collect(Collectors.toList());
    }
}