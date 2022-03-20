package kwic.kwicio;

import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Reader {
    public static String READ(String path) {
        try {
            return new String(Files.readAllBytes(Path.of(path)), StandardCharsets.UTF_8);
        } catch (Exception __) {
            return "";
        }
    }
}
