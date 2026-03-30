package akshatmittal61.models;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigurableParameters {
    List<ConfigurableParameter> parameters;
}
