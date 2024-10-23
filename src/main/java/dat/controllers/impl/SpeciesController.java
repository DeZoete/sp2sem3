package dat.controllers.impl;

import dat.controllers.IController;
import dat.daos.SpeciesDAO;
import dat.dtos.SpeciesDTO;
import io.javalin.http.Context;

import java.util.List;

public class SpeciesController implements IController<SpeciesDTO, Integer>{

    private SpeciesDAO speciesDAO;
    @Override
    public void read(Context ctx) {
        {
            int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
            SpeciesDTO speciesDTO = speciesDAO.read(id);
            ctx.res().setStatus(200);
            ctx.json(speciesDTO, SpeciesDTO.class);
        }
    }

    @Override
    public void readAll(Context ctx) {
    List<SpeciesDTO> speciesDTOS = speciesDAO.readAll();
    ctx.res().setStatus(200);
    ctx.json(speciesDTOS, SpeciesDTO.class);
    }

    @Override
    public void create(Context ctx) {
        SpeciesDTO jsonRequest = ctx.bodyAsClass(SpeciesDTO.class);
        // DTO
        SpeciesDTO speciesDTO = speciesDAO.create(jsonRequest);
        // response
        ctx.res().setStatus(201);
        ctx.json(speciesDTO, SpeciesDTO.class);
    }

    @Override
    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        SpeciesDTO speciesDTO = speciesDAO.update(id, validateEntity(ctx));
        ctx.res().setStatus(200);
        ctx.json(speciesDTO, SpeciesDTO.class);
    }

    @Override
    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        speciesDAO.delete(id);
        ctx.res().setStatus(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return false;
    }

    @Override
    public SpeciesDTO validateEntity(Context ctx) {
        return null;
    }
}
