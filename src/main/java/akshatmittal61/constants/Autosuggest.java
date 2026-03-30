package akshatmittal61.constants;

import akshatmittal61.models.ConfigurableParameter;
import java.util.ArrayList;
import java.util.List;

public class Autosuggest {

    public static final List<String> contentTypes = new ArrayList<>();
    public static final List<ConfigurableParameter> configurableParameters = new ArrayList<>();

    static {
        contentTypes.add("autosuggest/recentSearches");
        contentTypes.add("autosuggest/productOffersView");
        contentTypes.add("autosuggest/recommendedStores");
        contentTypes.add("autosuggest/trendingToday");
        contentTypes.add("autosuggest/popularProducts");

        configurableParameters.add(ConfigurableParameter.builder()
            .type("ENUMERATED")
            .key("CONTENT_TYPE")
            .displayName("Content Type")
            .multiInput(false)
            .dataType("STRING")
            .callbackUri("/sherlock/v1/autosuggest/management/getContentTypes")
            .searchable(true)
            .advanced(false)
            .uploadable(false)
            .build());
    }
}
