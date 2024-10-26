package dat.controllers.impl;

import dat.controllers.IController;
import dat.daos.impl.AnimalDAO;
import dat.dtos.AnimalDTO;
import dat.dtos.SpeciesDTO;
import dat.dtos.ZooDTO;
import io.javalin.http.Context;


import java.util.List;



public class AnimalController implements IController<AnimalDTO, Integer> {
    private AnimalDAO animalDAO;

    @Override
    public void read(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        AnimalDTO animalDTO = animalDAO.read(id);
        ctx.res().setStatus(200);
        ctx.json(animalDTO, AnimalDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        List<AnimalDTO> animals = animalDAO.readAll();  // Fetch all animals
        ctx.res().setStatus(200);  // Set response status
        ctx.json(animals, AnimalDTO.class);  // Send response as JSON
    }


    @Override
    public void create(Context ctx) {
        AnimalDTO jsonRequest = ctx.bodyAsClass(AnimalDTO.class); // Get the incoming JSON as AnimalDTO
        AnimalDTO animalDTO = animalDAO.create(jsonRequest); // Create the animal
        ctx.res().setStatus(201); // Set the response status to Created
        ctx.json(animalDTO); // Send back the created animal as JSON
    }

    @Override
    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get(); // Retrieve ID from path
        AnimalDTO animalDTO = validateEntity(ctx); // Validate and get the incoming JSON as AnimalDTO
        AnimalDTO updatedAnimalDTO = animalDAO.update(id, animalDTO); // Update the animal
        ctx.res().setStatus(200); // Set the response status to OK
        ctx.json(updatedAnimalDTO, AnimalDTO.class); // Send back the updated animal as JSON
    }

    @Override
    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class)
                .check(this::validatePrimaryKey, "Not a valid id")
                .get();

        animalDAO.delete(id);
        ctx.res().setStatus(204);
    }
    public void getZooByAnimalId(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        ZooDTO zooDTO = new ZooDTO(animalDAO.getZooByAnimalId(id));

        if (zooDTO != null) {
            ctx.res().setStatus(200);
            ctx.json(zooDTO);
        } else {
            ctx.res().setStatus(404);
            ctx.json("Zoo not found for the given animal ID.");
        }
    }


    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return animalDAO.validatePrimaryKey(integer);
    }

    @Override
    public AnimalDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(AnimalDTO.class)
                .check(a -> a.getAnimalName() != null && !a.getAnimalName().isEmpty(), "Animal name must be set")
                .check(a -> a.getAnimalAge() > 0, "Animal age must be greater than zero")
                .get();
    }
}

