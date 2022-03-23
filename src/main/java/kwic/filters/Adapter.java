package kwic.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Adapter {
    private static ArrayList<String> splitByLine(String text) {
        return text.lines().collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<String> splitBySpace(String text) {
        return new ArrayList<>(List.of(text.split(" ")));
    }

    public static ArrayList<String> ITERATE_KEYWORDS(String fileContent) {
        return splitByLine(fileContent).stream().filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ArrayList<String>> ITERATE_TITLES(String fileContent) {
        return splitByLine(fileContent).stream().filter(line -> !line.equals("")).map(line -> splitBySpace(line)).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String STRINGIFY(ArrayList<ArrayList<String>> _lines) {
        return _lines.stream().map(_line -> String.join(" ",_line)).collect(Collectors.joining("\n"));
    }
}
