package kwic.filters;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IO {
    public static String READ(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (Exception __) {
            return "";
        }
    }

    public static WriteFilter NEW_WRITE(String pathString) {
        return output -> {
            try {
                Files.write(Path.of(pathString), output.getBytes(StandardCharsets.UTF_8));
            } catch (Exception ignored) {
            }
        };
    }

    public interface WriteFilter {
        void write(String output);
    }
}
