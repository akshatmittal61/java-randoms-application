package akshatmittal61.constants;

import java.util.ArrayList;
import java.util.List;

public class Autosuggest {

    public static final List<String> contentTypes = new ArrayList<>();

    static {
        contentTypes.add("autosuggest/recentSearches");
        contentTypes.add("autosuggest/trendingSearches");
        contentTypes.add("autosuggest/recommendedStores");
        contentTypes.add("autosuggest/topDealsStores");
        contentTypes.add("autosuggest/popularProducts");
    }
}
