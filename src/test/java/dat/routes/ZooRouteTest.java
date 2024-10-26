package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.daos.impl.ZooDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.ZooDTO;
import dat.entities.Zoo;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ZooRouteTest {
    /*
    private static Javalin app;
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private static String BASE_URL = "http://localhost:7070/api/v1";
    private static ZooDAO zooDAO = new ZooDAO();

    private Zoo z1, z2;
    private ZooDTO z1DTO, z2DTO;

    @BeforeAll
    void setUpAll() {
        app = ApplicationConfig.startServer(7070);
        zooDAO = ZooDAO.getInstance(emf);
        try (EntityManager em = emf.createEntityManager()) {
            z1 = new Zoo("Zoo1", "location1");
            z2 = new Zoo("Zoo2", "location2");
            em.getTransaction().begin();
            em.persist(z1);
            em.persist(z2);
            em.getTransaction().commit();
            z1DTO = new ZooDTO(z1);
            z2DTO = new ZooDTO(z2);
        }
    }
    @BeforeEach
    void setUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Zoo").executeUpdate();
            z1 = new Zoo("Zoo1", "location1");
            z2 = new Zoo("Zoo2", "location2");
            em.persist(z1);
            em.persist(z2);
            em.getTransaction().commit();
            z1DTO = new ZooDTO(z1);
            z2DTO = new ZooDTO(z2);
        }
    }

    @AfterAll
    void closeDown() {
        ApplicationConfig.stopServer(app);
    }

    @Test
    void getAnimalById() {
        AnimalDTO animalDTO =
                given()
                        .when()
                        .get(BASE_URL + "/zoos/" + z1DTO.getZooId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(AnimalDTO.class);
        assertThat(animalDTO, equalTo(z1DTO));
    }

    @Test
    void testGetAll() {
        ZooDTO[] zooDTOSexpected =
                given()
                        .when()
                        .get(BASE_URL + "/zoos")
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ZooDTO[].class);
        assertEquals(2, zooDTOSexpected.length);
        assertThat(zooDTOSexpected, arrayContainingInAnyOrder(z1DTO, z2DTO));
    }

    @Test
    void deleteZoo() {
        given()
                .when()
                .delete(BASE_URL + "/zoos/" + z1DTO.getZooId())
                .then()
                .statusCode(204);
        List<ZooDTO> hotels = zooDAO.readAll();
        assertEquals(1, hotels.size());
        assertThat(hotels, not(hasItem(z1DTO)));
    }

    @Test
    void updateZoo() {
        ZooDTO updatedZoo = new ZooDTO(z1);
        updatedZoo.setZooName("Updated Zoo1");
        given()
                .contentType("application/json")
                .body(updatedZoo)
                .when()
                .put(BASE_URL + "/zoos/" + z1DTO.getZooId())
                .then()
                .statusCode(200);
        ZooDTO zoo =
                given()
                        .when()
                        .get(BASE_URL + "/zoos/" + z1DTO.getZooId())
                        .then()
                        .statusCode(200)
                        .extract()
                        .as(ZooDTO.class);
        assertThat(zoo.getZooName(), equalTo("Updated Zoo1"));
    }

    @Test
    void createZoo() {
        Zoo zoo = new Zoo("testzoo","testlocation");
        ZooDTO newZoo = new ZooDTO();
        ZooDTO createdZoo =
                given()
                        .contentType("application/json")
                        .body(newZoo)
                        .when()
                        .post(BASE_URL + "/zoo")
                        .then()
                        .statusCode(201)
                        .extract()
                        .as(ZooDTO.class);
        assertNotNull(createdZoo.getZooId());
        assertThat(newZoo.getZooName(), equalTo(createdZoo.getZooName()));
    }*/
}
