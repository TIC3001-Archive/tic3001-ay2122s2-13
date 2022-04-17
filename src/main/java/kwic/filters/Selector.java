package kwic.filters;


import java.util.*;
import java.util.stream.Collectors;

public class Selector {

    /**
     * The predicate interface which applied to a line will return if the line satisfies some condition.
     */
    @FunctionalInterface
    interface Predicate {
        boolean shouldSelect(ArrayList<String> line);
    }

    /**
     * If there are no specified required words, we assume required set of words is set of all strings.
     * <p>
     * Shifted Lines
     * We consider the case that the line may be shifted and the original line ended with a full stop.
     * That is, the first word of a target line may end with full stop. We may then strip the fullstop before applying the predicate function.
     * <p>
     * Example of filtering a shifted line:
     * Original Line : "I am a dog."
     * Decomposed: <I,am,a,dog.>
     * Shifted: <dog.,I,am,a>
     * Before Applying Predicate: <dog,I,am,a>
     *
     * @param requireSet set of words that intersects with the first word of a line.
     * @return predicate
     */
    public static Predicate newRequiredLineByFirstWordPredicateAgainstSet(HashSet<String> requireSet) {
        return line ->
                requireSet.size() == 0 || (requireSet.contains(line.get(0).replaceAll("\\.$", "").toLowerCase(Locale.ROOT)));
    }

    public static Predicate newRequiredLineByFirstWordAgainstSingleKeywordPredicate(String keyword) {

        return line -> line.get(0).replaceAll("\\.$", "").toLowerCase(Locale.ROOT).equals(keyword.toLowerCase(Locale.ROOT));
    }

    /**
     * @param ignoreSet set of words that DO NOT intersect with the first word of a line.
     * @return predicate that can be applied to a line
     */
    public static Predicate newIgnoreLinePredicate(HashSet<String> ignoreSet) {
        return line -> !ignoreSet.contains(line.get(0).toLowerCase(Locale.ROOT));
    }

    /**
     * The selection interface which applied to an array lines will return a subset of selected lines.
     */
    @FunctionalInterface
    public interface SelectionFilter {
        ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
    }

    /**
     * The selection interface which consumes a predicate function and returns a {@link SelectionFilter}.
     *
     * @param p
     * @return
     */
    public static SelectionFilter newRequireFilter(Predicate p) {
        return lines -> lines.stream().filter(p::shouldSelect).collect(Collectors.toCollection(ArrayList::new));
    }

}
