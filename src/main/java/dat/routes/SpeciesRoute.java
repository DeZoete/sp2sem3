package dat.routes;

import dat.controllers.impl.SpeciesController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class SpeciesRoute {

    private final SpeciesController speciesController = new SpeciesController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/", speciesController::create);
            get("/", speciesController::readAll);
            get("/{id}", speciesController::read);
            put("/{id}", speciesController::update);
            delete("/{id}", speciesController::delete);
            get("/{id}/zoo", speciesController::readAllZoos);
            get("/{id}/animals", speciesController::readAllAnimals);
        };
    }
}
