package kwic.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adapter {
    private static Stream<String> splitByLine(String text) {
        return text.lines();
    }

    private static ArrayList<String> splitBySpace(String text) {
        return new ArrayList<>(List.of(text.split(" ")));
    }

    public static ArrayList<String> ITERATE_KEYWORDS(String fileContent) {
        return splitByLine(fileContent).filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ArrayList<String>> ITERATE_TITLES(String fileContent) {
        return splitByLine(fileContent).filter(line -> !line.equals("")).map(Adapter::splitBySpace).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String STRINGIFY(ArrayList<ArrayList<String>> _lines) {
        return _lines.stream().map(_line -> String.join(" ",_line)).collect(Collectors.joining("\n"));
    }
}
