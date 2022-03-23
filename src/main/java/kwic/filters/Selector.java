package kwic.filters;


import java.util.*;

public class Selector {
    interface PredicateInterface {
        boolean shouldSelect(String word);
    }
    public interface SelectionInterface {
        ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
    }

    private static SelectionInterface NEW_SELECTION_FILTER(PredicateInterface p) {
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

    public static SelectionInterface NEW_FILTER_IGNORE(ArrayList<String> ignoreList) {
        final HashSet<String> ignoreSet = new HashSet<>();
        for (String ignore : ignoreList) {
            ignoreSet.add(ignore.toLowerCase(Locale.ROOT));
        }
        PredicateInterface p = word -> !ignoreSet.contains(word.toLowerCase(Locale.ROOT));
        return NEW_SELECTION_FILTER(p);
    }

    public static SelectionInterface NEW_FILTER_REQUIRE(ArrayList<String> requireList) {
        final HashSet<String> requireSet = new HashSet<>();
        for (String require : requireList) {
            requireSet.add(require.toLowerCase(Locale.ROOT));
        }
        return NEW_SELECTION_FILTER(requireSet.size() == 0 ? __ -> true : word -> requireSet.contains(word.toLowerCase(Locale.ROOT)));
    }



}
