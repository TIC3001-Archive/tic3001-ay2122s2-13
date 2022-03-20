package kwic.kwicio;

import java.nio.file.Files;
import java.nio.file.Path;

public class Reader {
    public static String READ(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (Exception __) {
            return "";
        }
    }
}
