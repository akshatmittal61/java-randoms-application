package akshatmittal61.routes;

import akshatmittal61.constants.Autosuggest;
import akshatmittal61.models.ConfigurableParameter;
import akshatmittal61.models.ConfigurableParameters;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Routes {

    @GET
    @Path("/api")
    public String api() {
        return "Hello World!";
    }

    @GET
    @Path("/elb-healthcheck")
    public String elbHealthCheck() {
        return "OK";
    }

    @GET
    @Path("/sherlock/v1/autosuggest/management/getContentTypes")
    public List<String> getContentTypes() {
        return Autosuggest.contentTypes;
    }

    @GET
    @Path("/sherlock/v1/autosuggest/management/getConfigurableParameters")
    public ConfigurableParameters getConfigurableParameters() {
        return ConfigurableParameters.builder().parameters(Autosuggest.configurableParameters)
            .build();
    }
}
