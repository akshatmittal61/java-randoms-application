package akshatmittal61.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigurableParameter {

    public String type;
    public String key;
    public String displayName;
    public boolean multiInput;
    public String dataType;
    public String callbackUri;
    public boolean searchable;
    public boolean advanced;
    public boolean uploadable;
}
