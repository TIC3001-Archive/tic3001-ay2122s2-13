package kwic.filters;


import java.util.*;
import java.util.stream.Collectors;

public class Selector {


    @FunctionalInterface
    interface Predicate {
        boolean shouldSelect(ArrayList<String> line);
    }




    public interface SelectionFilter {
        ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
    }

    public static SelectionFilter newIgnoreFilter(Predicate p) {
        return lines -> lines.stream().filter(line -> p.shouldSelect(line)).collect(Collectors.toCollection(ArrayList::new));
    }



    public static Predicate newRequiredLinePredicate(HashSet<String> requireSet) {
            return requireSet.size() == 0 ? __ -> true : line -> {
                String word = line.get(0);
                return  (requireSet.contains(word.replaceAll("\\.$", ""))) || requireSet.contains(word.toLowerCase(Locale.ROOT));
            };
    }

    public static Predicate newIgnoreLinePredicate(HashSet<String> ignoreSet) {
        return lines -> !ignoreSet.contains(lines.get(0).toLowerCase(Locale.ROOT));
    }

    public static SelectionFilter newRequireFilter(Predicate p) {
        return lines -> lines.stream().filter(line -> p.shouldSelect(line)).collect(Collectors.toCollection(ArrayList::new));
    }
}
