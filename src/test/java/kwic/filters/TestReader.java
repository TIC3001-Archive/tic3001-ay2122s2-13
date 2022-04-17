package kwic.filters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReader {
    @Test
    void Test_1() throws Exception {
        Assertions.assertEquals("", IO.read("./src/test/resources/test_read_source_1.txt"));
    }

    @Test
    void Test_2() throws Exception {
        assertEquals("a b c d" + System.lineSeparator() + "e f g h", IO.read("./src/test/resources/test_read_source_2.txt"));
    }

    @Test
    void Test_3() throws Exception {
        assertEquals("The Day after Tomorrow" + System.lineSeparator() + "Fast and Furious" + System.lineSeparator() + "Man of Steel", IO.read("./src/test/resources/test_read_source_3.txt"));
    }
}
