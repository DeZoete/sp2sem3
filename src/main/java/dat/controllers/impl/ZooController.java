package dat.controllers.impl;

import dat.config.HibernateConfig;
import dat.controllers.IController;
import dat.daos.impl.ZooDAO;
import dat.dtos.ZooDTO;
import dat.entities.Zoo;
import dat.exceptions.Message;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

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
        return null;
    }
}
