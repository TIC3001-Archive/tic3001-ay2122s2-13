package kwic.filters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Adapter {
    /**
     * Split content by EOL.
     * @param text
     * @return
     */
    private static Stream<String> splitByEol(String text) {
        return text.lines();
    }

    /**
     * Split content by whitespace.
     * @param text
     * @return
     */
    private static ArrayList<String> splitByWhitespace(String text) {
        return new ArrayList<>(List.of(text.split(" ")));
    }

    /**
     * Split raw content by EOL, then remove empty-sized elements
     * @param fileContent
     * @return
     */
    public static ArrayList<String> iterateKeywords(String fileContent) {
        return splitByEol(fileContent).filter(word -> !word.equals("")).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Replaces the line delimiter " " with EOL in text with paragraphs.
     * @param fileContent
     * @return
     */
    public static String delimitLinesInParagraphWithEOL(String fileContent) {
        return fileContent.replaceAll("\\. ", "." + System.lineSeparator());
    }

    /**
     * Set representation of a set of strings.
     * @param words
     * @return
     */
    public static HashSet<String> arrayListToHashSet(ArrayList<String> words) {
        return (HashSet<String>)words.stream().map(word -> word.toLowerCase(Locale.ROOT)).collect(Collectors.toSet());
    }

    /**
     * take in raw content
     *
     * @param lineDelimitedTitles
     * @return
     */
    public static ArrayList<ArrayList<String>> iterateTitles(String lineDelimitedTitles) {
        return splitByEol(lineDelimitedTitles).filter(line -> !line.equals("")).map(Adapter::splitByWhitespace).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Transforms lines to text representation for external use.
     * @param _lines
     * @return
     */
    public static String stringify(ArrayList<ArrayList<String>> _lines) {
        return _lines.stream().map(_line -> String.join(" ", _line)).collect(Collectors.joining("\n"));
    }
}
