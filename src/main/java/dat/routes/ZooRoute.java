package dat.routes;

import dat.controllers.impl.ZooController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.delete;

public class ZooRoute {
    private final ZooController zooController = new ZooController();

    protected EndpointGroup getRoutes() {

        return () -> {
            post("/", zooController::create);
            get("/", zooController::readAll);
            get("/{id}", zooController::read);
            put("/{id}", zooController::update);
            delete("/{id}", zooController::delete);
        };
    }
}
