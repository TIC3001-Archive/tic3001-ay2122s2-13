package kwic.kwicio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestReader {
    @Test
    void Test_1() {
        assertEquals("", Reader.READ("./src/test/resources/test_read_source_1.txt"));
    }

    @Test
    void Test_2() {
        assertEquals("a b c d" + System.lineSeparator() + "e f g h", Reader.READ("./src/test/resources/test_read_source_2.txt"));
    }

    @Test
    void Test_3() {
        assertEquals("The Day after Tomorrow" + System.lineSeparator() + "Fast and Furious" + System.lineSeparator() + "Man of Steel", Reader.READ("./src/test/resources/test_read_source_3.txt"));
    }
}
