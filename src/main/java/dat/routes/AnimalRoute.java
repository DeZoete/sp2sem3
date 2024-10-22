package dat.routes;

import dat.controllers.impl.AnimalController;
import dat.entities.Animal;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
public class AnimalRoute {
    private final AnimalController roomController = new AnimalController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/hotel/{id}", roomController::create);
            get("/", roomController::readAll);
            get("/{id}", roomController::read);
            put("/{id}", roomController::update);
            delete("/{id}", roomController::delete);
        };
    }

}

