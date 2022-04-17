package kwic.filters;


import java.util.*;

public class Selector {
    interface Predicate {
        boolean shouldSelect(String word);
    }

    public interface SelectionFilter {
        ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
    }

    private static SelectionFilter newLineSelectionByFirstWordFilter(Predicate p) {
        return lines -> {
            ArrayList<ArrayList<String>> filtered = new ArrayList<>();
            for (ArrayList<String> words : lines) {
                String firstWord = words.get(0);
                if (p.shouldSelect(firstWord)) {
                    filtered.add(words);
                }
            }
            return filtered;
        };
    }

    public static SelectionFilter newFilterIgnore(ArrayList<String> ignoreList) {
        final HashSet<String> ignoreSet = new HashSet<>();
        for (String ignore : ignoreList) {
            ignoreSet.add(ignore.toLowerCase(Locale.ROOT));
        }
        Predicate p = word -> !ignoreSet.contains(word.toLowerCase(Locale.ROOT));
        return newLineSelectionByFirstWordFilter(p);
    }

    public static SelectionFilter newRequireFilter(ArrayList<String> requireList) {
        final HashSet<String> requireSet = new HashSet<>();
        for (String require : requireList) {
            requireSet.add(require.toLowerCase(Locale.ROOT));
        }
        return newLineSelectionByFirstWordFilter(requireSet.size() == 0 ? __ -> true : word -> requireSet.contains(word + ".") || requireSet.contains(word.toLowerCase(Locale.ROOT)));
    }
}
