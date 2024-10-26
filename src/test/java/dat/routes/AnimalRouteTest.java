package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.daos.impl.AnimalDAO;
import dat.dtos.AnimalDTO;
import dat.entities.Animal;

import dat.entities.Zoo;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimalRouteTest {
    private static Javalin app;
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static String BASE_URL = "http://localhost:7070/api/v1";
    private static AnimalDAO animalDAO = new AnimalDAO();
    private Animal a1, a2, a3;
    private AnimalDTO a1DTO, a2DTO, a3DTO;
    private Set<Animal> animals = new HashSet<>();
    private Zoo z1, z2;
    @BeforeAll
    void setUpAll() {
        app = ApplicationConfig.startServer(7070);
        animalDAO = AnimalDAO.getInstance(emf);
        try (EntityManager em = emf.createEntityManager()) {
            z1 = new Zoo("zoo", "location");
            a1 = new Animal("Georg", 2);
            a2 = new Animal("Lars", 3);
            a3 = new Animal("Niels",4);
            animals.add(a1);
            animals.add(a2);
            animals.add(a3);
            z1.setAnimals(animals);
            em.getTransaction().begin();
            em.persist(z1);
            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.getTransaction().commit();
            a1DTO = new AnimalDTO(a1);
            a2DTO = new AnimalDTO(a2);
            a3DTO = new AnimalDTO(a3);
        }
    }


    @BeforeEach
    void setUp() {
    }
    @AfterEach
    void tearDown() {
    }

    @AfterAll
    void closeDown() {
            ApplicationConfig.stopServer(app);
    }

    @Test
    void testGetAnimalById(){
        AnimalDTO animal =
                given()
                        .when()
                        .get(BASE_URL + "/animals/" + a1.getAnimalId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(AnimalDTO.class);
        assertThat(animal, equalTo(a1DTO));
    }

    @Test
    void testGetAll(){
        AnimalDTO[] animalDTOSexpected =
                given()
                        .when()
                        .get(BASE_URL + "/animals")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(AnimalDTO[].class);
        assertEquals(3, animalDTOSexpected.length);
        assertThat(animalDTOSexpected, arrayContainingInAnyOrder(a1DTO, a2DTO, a3DTO));
    }

    @Test
    void deleteAnimal(){
        given()
                .when()
                .delete(BASE_URL + "/animals/" + a1.getAnimalId())
                .then()
                .statusCode(204);
        List<AnimalDTO> animals = animalDAO.readAll();
        assertEquals(2, animals.size());
        assertThat(animals, not(hasItem(a1DTO)));
    }

    @Test
    void updateAnimal(){
        AnimalDTO updatedAnimal = new AnimalDTO(a1);
        updatedAnimal.setAnimalName("updated Georg");
        given()
                .contentType("application/json")
                .body(updatedAnimal)
                .when()
                .put(BASE_URL + "/animals/" + a1.getAnimalId())
                .then()
                .statusCode(200);
        AnimalDTO animal =
                given()
                        .when()
                        .get(BASE_URL + "/animals/" + a1.getAnimalId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(AnimalDTO.class);
        assertThat(animal.getAnimalId(), equalTo("updated Georg"));
    }
    @Test
    void createAnimal(){
        AnimalDTO newAnimal = new AnimalDTO(a1);
        AnimalDTO createdAnimal =
        given()
                .contentType("application/json")
                .body(newAnimal)
                .when()
                .post(BASE_URL + "/animals/zoo/" + newAnimal.getAnimalId())
                .then()
                .statusCode(201)
                .extract()
                .as(AnimalDTO.class);
        assertThat(newAnimal, equalTo(createdAnimal));
    }
}