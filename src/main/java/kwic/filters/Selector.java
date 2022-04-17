package kwic.filters;


import java.util.*;
import java.util.stream.Collectors;

public class Selector {

    @FunctionalInterface
    interface Predicate {
        boolean shouldSelect(String word);
    }

    @FunctionalInterface
    interface Predicate2 {
        boolean shouldSelect(ArrayList<String> line);
    }

    private static SelectionFilter newLineSelectionByFirstWordFilter2(Predicate2 p){
        return lines -> lines.stream().filter(l -> p.shouldSelect(l)).collect(Collectors.toCollection(ArrayList::new));
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

    public static HashSet<String> toHashSet (ArrayList<String> words) {
        return (HashSet<String>)words.stream().map(word -> word.toLowerCase(Locale.ROOT)).collect(Collectors.toSet());
    }

    public static Predicate2 newPredicate2(HashSet<String> requireSet) {
            return requireSet.size() == 0 ? __ -> true : line -> {
                String word = line.get(0);
                return  (requireSet.contains(word.replaceAll("\\.$", ""))) || requireSet.contains(word.toLowerCase(Locale.ROOT));
            };
    }

    public static SelectionFilter newRequireLinesFilter (Predicate2 p) {
        return lines -> lines.stream().filter(line -> p.shouldSelect(line)).collect(Collectors.toCollection(ArrayList::new));
    }



    public static SelectionFilter newRequireFilter(ArrayList<String> requireList) {
        final HashSet<String> requireSet = new HashSet<>();
        for (String require : requireList) {
            requireSet.add(require.toLowerCase(Locale.ROOT));
        }
        return newLineSelectionByFirstWordFilter(requireSet.size() == 0 ? __ -> true : word -> (requireSet.contains(word.replaceAll("\\.$", ""))) || requireSet.contains(word.toLowerCase(Locale.ROOT)));
    }
}
