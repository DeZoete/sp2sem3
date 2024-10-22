package dat.routes;

import dat.controllers.impl.AnimalController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
public class AnimalRoute {
    private final AnimalController animalController = new AnimalController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/", animalController::create);
            get("/", animalController::readAll);
            get("/{id}", animalController::read);
            put("/{id}", animalController::update);
            delete("/{id}", animalController::delete);
        };
    }

}

