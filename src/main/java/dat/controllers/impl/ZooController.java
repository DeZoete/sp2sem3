package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.impl.ZooDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.SpeciesDTO;
import dat.dtos.ZooDTO;
import dat.entities.Zoo;
import dat.exceptions.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ZooController implements IController<ZooDTO, Integer> {

    private ZooDAO dao;

    public ZooController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("animal");
        this.dao = ZooDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) {
        // request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        // entity
        ZooDTO zooDTO = dao.read(id);
        // response
        ctx.res().setStatus(200);
        ctx.json(zooDTO, ZooDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        // entity
        List<ZooDTO> zooDTOS = dao.readAll();
        // response
        ctx.res().setStatus(200);
        ctx.json(zooDTOS, ZooDTO.class);

    }

    @Override
    public void create(Context ctx) {
        // request
        ZooDTO jsonRequest = ctx.bodyAsClass(ZooDTO.class);
        // DTO
        ZooDTO zooDTO = dao.create(jsonRequest);
        // response
        ctx.res().setStatus(201);
        ctx.json(zooDTO, ZooDTO.class);
    }

    @Override
    public void update(Context ctx) {
        // request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        // dto
        ZooDTO zooDTO = dao.update(id, validateEntity(ctx));
        // response
        ctx.res().setStatus(200);
        ctx.json(zooDTO, ZooDTO.class);
    }

    @Override
    public void delete(Context ctx) {
        // request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        dao.delete(id);
        // response
        ctx.res().setStatus(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }

    @Override
    public ZooDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(ZooDTO.class)
                .check( z -> z.getZooName() != null && !z.getZooName().isEmpty(), "Zoo name must be set")
                .check( z -> z.getZooLocation() != null && !z.getZooLocation().isEmpty(), "Zoo location must be set")
                .get();
    }

    public void readAllAnimals(Context ctx) {
        // request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        // entity
        List<AnimalDTO> animalDTOS = dao.readAllAnimals(id);
        // response
        ctx.res().setStatus(200);
        ctx.json(animalDTOS, AnimalDTO.class);
    }

    public void readAllSpecies(Context ctx) {
        // request
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        // entity
        List<SpeciesDTO> species = dao.readAllSpecies(id);
        // response
        ctx.res().setStatus(200);
        ctx.json(species);
    }
}
