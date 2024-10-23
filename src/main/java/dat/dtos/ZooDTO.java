package dat.dtos;

import dat.entities.Zoo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ZooDTO {

    private int zooId;
    private String zooName;
    private String zooLocation;
    private Set<AnimalDTO> animals = new HashSet<>();

    public ZooDTO(Zoo zoo) {
        this.zooId = zoo.getZooId();
        this.zooName = zoo.getZooName();
        this.zooLocation = zoo.getZooLocation();
        if (zoo.getAnimals() != null)
        {
            zoo.getAnimals().forEach( animal -> animals.add(new AnimalDTO(animal)));
        }
    }

    public ZooDTO(String name, String location) {
        this.zooName = name;
        this.zooLocation = location;
    }

    public static List<ZooDTO> toZooDTOList(List<Zoo> zooList) {
        return zooList.stream().map(ZooDTO::new).collect(Collectors.toList());
    }
}