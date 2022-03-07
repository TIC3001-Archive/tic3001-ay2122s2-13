package kwic;


import java.io.FileWriter;
import java.io.IOException;

public class Writer {


    private String path = null;


    private Writer() {
    }

    ;

    public Writer(String path) {
        this.path = path;
    }

    public void writeAndClose(String output) throws Exception {
        try {
            FileWriter writer = new FileWriter(this.path, false);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
