package kwic.filters;


import java.util.*;

public class Selector {
    interface Predicate {
        boolean shouldSelect(String word);
    }
    public interface SelectionFilter {
        ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
    }

    private static SelectionFilter NEW_SELECTION_FILTER(Predicate p) {
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

    public static SelectionFilter NEW_FILTER_IGNORE(ArrayList<String> ignoreList) {
        final HashSet<String> ignoreSet = new HashSet<>();
        for (String ignore : ignoreList) {
            ignoreSet.add(ignore.toLowerCase(Locale.ROOT));
        }
        Predicate p = word -> !ignoreSet.contains(word.toLowerCase(Locale.ROOT));
        return NEW_SELECTION_FILTER(p);
    }

    public static SelectionFilter NEW_FILTER_REQUIRE(ArrayList<String> requireList) {
        final HashSet<String> requireSet = new HashSet<>();
        for (String require : requireList) {
            requireSet.add(require.toLowerCase(Locale.ROOT));
        }
        return NEW_SELECTION_FILTER(requireSet.size() == 0 ? __ -> true : word -> requireSet.contains(word.toLowerCase(Locale.ROOT)));
    }
}
