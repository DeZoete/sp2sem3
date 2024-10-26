package dat.entities;



import dat.dtos.SpeciesDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "species_id", nullable = false, unique = true)
    private int speciesId;

    @Column(name = "species_name", nullable = false, unique = true)
    private String speciesName;

    @Column(name = "diet", nullable = false)
    private String diet;

    @Column(name = "habitat", nullable = false)
    private String habitat;

    public Species(String speciesName, String diet, String habitat) {
        this.speciesName = speciesName;
        this.diet = diet;
        this.habitat = habitat;
    }

    public Species(SpeciesDTO speciesDTO) {
        this.speciesId = speciesDTO.getSpeciesId();
        this.speciesName = speciesDTO.getSpeciesName();
        this.diet = speciesDTO.getDiet();
        this.habitat = speciesDTO.getHabitat();
        }
    }
