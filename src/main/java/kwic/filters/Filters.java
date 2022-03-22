package kwic.filters;


import java.util.*;
import java.util.stream.Collectors;


interface PredicateInterface {
    boolean shouldSelect(String word);
}

public class Filters {

    private static FilterInterface NEW_FILTER(PredicateInterface p) {
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

        return NEW_FILTER(requireSet.size() == 0 ? __ -> true : word -> requireSet.contains(word.toLowerCase(Locale.ROOT)));
    }

    private static ArrayList<String> splitByLine(String text) {
        return text.lines().collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<String> splitBySpace(String text) {
        return new ArrayList<>(List.of(text.split(" ")));
    }

    public static ArrayList<String> ITERATE_KEYWORDS_LSV(String content) {
        return splitByLine(content).stream().filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<String> ITERATE_TITLE(String content) {
        return splitBySpace(content).stream().filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ArrayList<String>> ITERATE_TITLES(String content) {
        return splitByLine(content).stream().filter(line -> !line.equals("")).map(Filters::ITERATE_TITLE).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String STRINGIFY(ArrayList<ArrayList<String>> _lines) {
        ArrayList<String> lines = new ArrayList<>();
        for (ArrayList<String> _line : _lines) {
            String line = String.join(" ", _line);
            lines.add(line);
        }
        return String.join("\n", lines);
    }

    public static ArrayList<ArrayList<String>> SHIFT(ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> shiftedLinesAll = new ArrayList<>();
        for (ArrayList<String> words : lines) {
            ArrayList<ArrayList<String>> thisShiftedLines = new ArrayList<>();
            int length = words.size();
            for (int i = 0; i < length; i++) {
                ArrayList<String> _words = new ArrayList<>();
                for (int j = 0; j < length; j++) {
                    _words.add(words.get((i + j) % length));
                }
                thisShiftedLines.add(_words);
            }
            shiftedLinesAll.addAll(thisShiftedLines);
        }
        return shiftedLinesAll;
    }

    public static ArrayList<ArrayList<String>> LEXI(ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> alphabetized = new ArrayList<>(lines);

        alphabetized.sort(Comparator.comparing((ArrayList<String> a) -> String.join(" ", a).toLowerCase(Locale.ROOT)));
        return alphabetized;
    }
}
