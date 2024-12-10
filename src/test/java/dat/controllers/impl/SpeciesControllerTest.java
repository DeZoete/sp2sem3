package dat.controllers.impl;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.PopulateForTest;
import dat.controllers.impl.SpeciesController;
import dat.dtos.SpeciesDTO;
import io.javalin.Javalin;
import io.restassured.common.mapper.TypeRef;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpeciesControllerTest {

    private static EntityManagerFactory emf;
    private static Javalin app;
    private static final String BASE_URL = "http://localhost:7070/api/v1";

    @BeforeAll
    void setUpAll() {
        // Start the Javalin API server

        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactoryForTest();
        app = ApplicationConfig.startServer(7070);
    }

    @BeforeEach
    void setUp() {
        // Populate the database with initial data before each test
        System.out.println("Populating database with species, zoos, and animals");
        PopulateForTest.Poulate();
    }

    @AfterEach
    void tearDown() {
        // Clean up database after each test
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Animal").executeUpdate();
            em.createQuery("DELETE FROM Zoo").executeUpdate();
            em.createQuery("DELETE FROM Species").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    void tearDownAll() {
        // Stop the Javalin server after all tests
        ApplicationConfig.stopServer(app);
    }

    @Test
    void readAllSpecies() {
        // Retrieve all species and validate the result
        List<SpeciesDTO> speciesDTOList =
                given()
                        .when()
                        .get(BASE_URL + "/species")
                        .then()
                        .statusCode(200)
                        .body("size()", is(4))  // Assuming 5 species are populated by Populate
                        .log().all()
                        .extract()
                        .as(new TypeRef<List<SpeciesDTO>>() {});

        assertThat(speciesDTOList.size(), is(4));

    }

    @Test
    void readSpeciesById() {
        // Assume "Elephant" is a valid species entry in the database with ID 1
        int speciesId = 3;

        SpeciesDTO speciesDTO =
                given()
                        .when()
                        .get(BASE_URL + "/species/" + speciesId)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(SpeciesDTO.class);

        assertThat(speciesDTO.getSpeciesName(), is("Elephant"));
    }

    @Test
    void createSpecies() {
        // Create a new SpeciesDTO for the test
        SpeciesDTO newSpecies = new SpeciesDTO();
        newSpecies.setSpeciesName("Jones");
        newSpecies.setDiet("tunshake");
        newSpecies.setHabitat("Room");

        // Send POST request to create the new species
        SpeciesDTO createdSpecies =
                given()
                        .contentType("application/json")
                        .body(newSpecies)
                        .when()
                        .post(BASE_URL + "/species")
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract()
                        .as(SpeciesDTO.class);

        // Verify the creation
        assertThat(createdSpecies.getSpeciesName(), is("Jones"));
        assertThat(createdSpecies.getDiet(), is("tunshake"));
        assertThat(createdSpecies.getHabitat(), is("Room"));
    }

    @Test
    void updateSpecies() {
        // Assume "Lion" is a valid species entry in the database with ID 1
        int speciesId = 3;

        // Update the species name
        SpeciesDTO updatedSpecies = new SpeciesDTO();
        updatedSpecies.setSpeciesName("Updated Lion");
        updatedSpecies.setDiet("Carnivore");
        updatedSpecies.setHabitat("Savannah");

        // Send PUT request to update the species
        SpeciesDTO responseSpecies =
                given()
                        .contentType("application/json")
                        .body(updatedSpecies)
                        .when()
                        .put(BASE_URL + "/species/" + speciesId)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .as(SpeciesDTO.class);

        // Verify the update
        assertThat(responseSpecies.getSpeciesName(), is("Updated Lion"));
    }

    @Test
    void deleteSpecies() {
        // Assume "Lion" is a valid species entry in the database with ID 1
        int speciesId = 4;

        // Send DELETE request to remove the species
        given()
                .when()
                .delete(BASE_URL + "/species/" + speciesId)
                .then()
                .statusCode(204)
                .log().all();

        // Verify the deletion by trying to read the deleted species
        given()
                .when()
                .get(BASE_URL + "/species/" + speciesId)
                .then()
                .statusCode(400);  // Assuming 404 is returned when a species is not found
    }
}
