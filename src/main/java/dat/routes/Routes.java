package dat.routes;

import dat.entities.Animal;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final AnimalRoute animalRoute = new AnimalRoute();
    private final SpeciesRoute speciesRoute = new SpeciesRoute();
    private final ZooRoute zooRoute = new ZooRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/animals", animalRoute.getRoutes());
            path("/species", speciesRoute.getRoutes());
            path("/zoos", zooRoute.getRoutes());

        };
    }
}
