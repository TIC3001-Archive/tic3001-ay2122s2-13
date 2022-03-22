package kwic;

import kwic.filters.FilterInterface;
import kwic.filters.Filters;
import kwic.kwicio.Writer;
import kwic.kwicio.WriterInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static kwic.kwicio.Reader.READ;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAdapters {

    @Test
    void Test_Assignment2_Core_001() {

        String txtSource = "Back to the Future";
        ArrayList<ArrayList<String>> sourceLines = Filters.ITERATE_TITLES(txtSource);
        assertEquals(1, sourceLines.size());
        assertEquals(4, sourceLines.get(0).size());

        ArrayList<ArrayList<String>> shiftedLines = Filters.SHIFT(sourceLines);

        assertEquals(4, shiftedLines.size());
        assertEquals("Back", shiftedLines.get(0).get(0));
        assertEquals("to", shiftedLines.get(1).get(0));
        assertEquals("the", shiftedLines.get(2).get(0));
        assertEquals("Future", shiftedLines.get(3).get(0));


        ArrayList<ArrayList<String>> sortedShiftedLines = Filters.LEXI(shiftedLines);

        assertEquals(4, sortedShiftedLines.size());
        assertEquals("Back", sortedShiftedLines.get(0).get(0));
        assertEquals("Future", sortedShiftedLines.get(1).get(0));
        assertEquals("the", sortedShiftedLines.get(2).get(0));
        assertEquals("to", sortedShiftedLines.get(3).get(0));

        String output = Filters.STRINGIFY(sortedShiftedLines);

        assertEquals("Back to the Future\nFuture Back to the\nthe Future Back to\nto the Future Back", output);

    }


    @Test
    void Test_Assignment2_Core_002() {
        String sourcePath = "./sample_test_cases/Test1/Titles1.txt";
        String txtSource = READ(sourcePath);

        ArrayList<ArrayList<String>> sourceLines = Filters.ITERATE_TITLES(txtSource);


        assertEquals(8, sourceLines.size());
        assertEquals(4, sourceLines.get(0).size());
        assertEquals(1, sourceLines.get(1).size());
        assertEquals(4, sourceLines.get(2).size());
        assertEquals(1, sourceLines.get(3).size());
        assertEquals(1, sourceLines.get(4).size());
        assertEquals(2, sourceLines.get(5).size());
        assertEquals(2, sourceLines.get(6).size());
        assertEquals(2, sourceLines.get(7).size());

        ArrayList<ArrayList<String>> shiftedLines = Filters.SHIFT(sourceLines);

        assertEquals(17, shiftedLines.size());



        ArrayList<ArrayList<String>> sortedShiftedLines = Filters.LEXI(shiftedLines);

        assertEquals(17, sortedShiftedLines.size());
        assertEquals("at", sortedShiftedLines.get(0).get(0));
        assertEquals("the", sortedShiftedLines.get(0).get(1));
        assertEquals("Museum", sortedShiftedLines.get(0).get(2));
        assertEquals("Night", sortedShiftedLines.get(0).get(3));
        assertEquals("Back", sortedShiftedLines.get(1).get(0));
        assertEquals("Cool", sortedShiftedLines.get(2).get(0));

        assertEquals("Darko", sortedShiftedLines.get(3).get(0));
        assertEquals("Desperado", sortedShiftedLines.get(4).get(0));
        assertEquals("Donnie", sortedShiftedLines.get(5).get(0));
        assertEquals("Double", sortedShiftedLines.get(6).get(0));
        assertEquals("Future", sortedShiftedLines.get(7).get(0));
        assertEquals("World", sortedShiftedLines.get(16).get(0));

        String ignorePath = "./sample_test_cases/Test1/Ignored1.txt";
        ArrayList<String> ignores = Filters.ITERATE_KEYWORDS_LSV(READ(ignorePath));

        assertEquals(0, ignores.size());
        FilterInterface fIgnore = Filters.NEW_FILTER_IGNORE(ignores);
        ArrayList<ArrayList<String>> whiteList = fIgnore.filter(sortedShiftedLines);
        assertEquals(17, whiteList.size());


        String requirePath = "./sample_test_cases/Test1/Required1.txt";
        ArrayList<String> requires = Filters.ITERATE_KEYWORDS_LSV(READ(requirePath));
        FilterInterface fRequire = Filters.NEW_FILTER_REQUIRE(requires);
        ArrayList<ArrayList<String>> greenList = fRequire.filter(sortedShiftedLines);
        assertEquals(17, greenList.size());

        String output = Filters.STRINGIFY(greenList);

        String outputPath = sourcePath.replace(".txt", "-output.txt");
        WriterInterface pWriter = Writer.NEW_WRITE(outputPath);
        pWriter.write(output);
    }

}
