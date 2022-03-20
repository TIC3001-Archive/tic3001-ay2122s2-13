package kwic.filters;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;


interface FilterInterface {
    ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
}

interface PredicateInterface {
    boolean shouldSelect (String word);
}



public class Filters {

    private static FilterInterface NEW_FILTER(PredicateInterface p){
        return lines -> {
            ArrayList<ArrayList<String>> filtered = new ArrayList<>();
            for (ArrayList<String> words : lines) {
                if (p.shouldSelect(words.get(0))) {
                    filtered.add(words);
                }
            }
            return filtered;
        };

    }
    public static FilterInterface NEW_FILTER_IGNORE(ArrayList<String> ignoreList) {
        final HashSet<String> ignoreSet = new HashSet<>();
        for (String ignore : ignoreList) {
            ignoreSet.add(ignore.toLowerCase(Locale.ROOT));
        }
        PredicateInterface p = word -> !ignoreSet.contains(word.toLowerCase(Locale.ROOT));
        return NEW_FILTER(p);
    }

    public static FilterInterface NEW_FILTER_REQUIRE(ArrayList<String> requireList) {
        final HashSet<String> requireSet = new HashSet<>();
        for (String require : requireList) {
            requireSet.add(require.toLowerCase(Locale.ROOT));
        }
        PredicateInterface p = word -> requireSet.contains(word.toLowerCase(Locale.ROOT));
        return NEW_FILTER(p);
    }

}
