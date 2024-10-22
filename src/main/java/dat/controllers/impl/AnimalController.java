package dat.controllers.impl;

import dat.controllers.IController;
import dat.dtos.AnimalDTO;
import io.javalin.http.Context;

public class AnimalController implements IController<AnimalDTO, Integer> {
    @Override
    public void read(Context ctx) {

    }

    @Override
    public void readAll(Context ctx) {

    }

    @Override
    public void create(Context ctx) {

    }

    @Override
    public void update(Context ctx) {

    }

    @Override
    public void delete(Context ctx) {

    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return false;
    }

    @Override
    public AnimalDTO validateEntity(Context ctx) {
        return null;
    }
}
