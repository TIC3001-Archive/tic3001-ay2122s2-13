package kwic.filters;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TestWriter {
    @Test
    void Test_WriteToFile() {
        String[] inputs = {"a", "a b", "a b\nc d"};
        int length = inputs.length;
        for (int i = 0; i < length; i++) {
            String input = inputs[i];
            String pathString = "./residue/test_read_source_" + i + ".txt";
            IO.OutFilter writer = IO.newWriteToFileOutFilter(pathString);
            writer.write(input);
            try {
                String actual = Files.readString(Path.of(pathString));
                assertEquals(input, actual);
            } catch (Exception err) {
                fail("Read operation error.");
            }

        }
    }


}
