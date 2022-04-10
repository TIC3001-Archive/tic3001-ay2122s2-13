package kwic.filters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adapter {
    private static Stream<String> splitByEol(String text) {
        return text.lines();
    }

    private static ArrayList<String> splitByWhitespace(String text) {
        return new ArrayList<>(List.of(text.split(" ")));
    }

    public static ArrayList<String> iterateKeywords(String fileContent) {
        return splitByEol(fileContent).filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<ArrayList<String>> iterateTitles(String fileContent) {
        return splitByEol(fileContent).filter(line -> !line.equals("")).map(Adapter::splitByWhitespace).collect(Collectors.toCollection(ArrayList::new));
    }

    public static String stringify(ArrayList<ArrayList<String>> _lines) {
        return _lines.stream().map(_line -> String.join(" ", _line)).collect(Collectors.joining("\n"));
    }
}
