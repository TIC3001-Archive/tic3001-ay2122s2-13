package kwic.kwicio;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWriter {
    @Test
    void Test_Batch_1() {
        String[] inputs = {"a","a b","a b\nc d"};
        int length = inputs.length;
        for (int i = 0; i < length; i++) {
            String input = inputs[i];
            String pathString = "./residue/test_read_source_" + i + ".txt";
            WriterInterface writer = Writer.NEW_WRITE(pathString);
            writer.write(input);
            try {
                String actual = new String(Files.readAllBytes(Path.of(pathString)), StandardCharsets.UTF_8);
                assertEquals(input, actual);
            } catch (Exception err) {
                assertTrue(false, "Read operation error.");
            }

        }
    }


}
