package akshatmittal61;

import akshatmittal61.routes.Routes;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RandomsApplication extends Application<RandomsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RandomsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Randoms";
    }

    @Override
    public void initialize(final Bootstrap<RandomsConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final RandomsConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new Routes());
    }

}
