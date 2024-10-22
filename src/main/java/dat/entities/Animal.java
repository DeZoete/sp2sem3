package dat.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "species_id", nullable = false)
    private Integer speciesId;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zoo_id", nullable = false)
    private Zoo zoo;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "age", nullable = false)
    private Integer age;

    public Animal(String name, Integer age, Integer speciesId) {
        this.name = name;
        this.age = age;
        this.speciesId = speciesId;
    }
}
