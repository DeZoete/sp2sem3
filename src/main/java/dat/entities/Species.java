package dat.entities;

import dat.dtos.SpeciesDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Setter
    private String speciesName;

    @Column
    @Setter
    private String diet;


    @Column
    @Setter
    private String habitat;

    @ManyToOne
    @Setter
    private Zoo zoo;

    public Species(SpeciesDTO speciesDTO) {
        this.id = speciesDTO.getId();
        this.speciesName = speciesDTO.getSpeciesName();
        this.diet = speciesDTO.getDiet();
        this.habitat = speciesDTO.getHabitat();

    }
    public Species(String speciesName, String diet, String habitat) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
        this.zoo = zoo;
    }
}
