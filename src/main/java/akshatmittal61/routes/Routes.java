package akshatmittal61.routes;

import akshatmittal61.models.ConfigurableParameters;
import akshatmittal61.client.HttpClientService;
import java.io.IOException;
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

    private final HttpClientService httpClientService;

    public Routes() {
        this.httpClientService = new HttpClientService();
    }

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
    public List<?> getContentTypes() throws IOException {
        return httpClientService.get(
            "http://10.24.36.140/sherlock/v1/autosuggest/management/getContentTypes", List.class);
    }

    @GET
    @Path("/sherlock/v1/autosuggest/management/getConfigurableParameters")
    public ConfigurableParameters getConfigurableParameters() throws IOException {
        return httpClientService.get(
            "http://10.24.36.140/sherlock/v1/autosuggest/management/getConfigurableParameters",
            ConfigurableParameters.class);
    }
}
