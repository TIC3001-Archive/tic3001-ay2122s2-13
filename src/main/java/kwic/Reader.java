package kwic;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {


    private String path = null;
    private ArrayList<String> lineStrings = null;

    private Reader() {
    }

    ;

    public Reader(String path) {
        this.path = path;
    }

    public ArrayList<String> getContent() {
        if (lineStrings == null) {
            try {
                lineStrings = new ArrayList<>();
                File myObj = new File(this.path);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    lineStrings.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred." + e.toString());
            }
        }
        return lineStrings;
    }


}
