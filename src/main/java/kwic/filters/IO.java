package kwic.filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class IO {

    public static String read(String path) throws IOException {
        try {
            return Files.readString(Path.of(path));
        } catch (NoSuchFileException err) {
            throw new NoSuchFileException("Error opening file " + path + " (No Such File Exception)");
        }
    }

    @FunctionalInterface
    public interface OutFilter {
        void write(String output);
    }

    public static OutFilter newWriteToFileOutFilter(String pathString) {
        return output -> {
            try {
                Files.write(Path.of(pathString), output.getBytes(StandardCharsets.UTF_8));
            } catch (Exception ignored) {
            }
        };
    }

    public static OutFilter newSystemOutFilter() {
        return System.out::print;
    }


}
