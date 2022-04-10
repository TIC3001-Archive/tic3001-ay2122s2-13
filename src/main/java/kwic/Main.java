package kwic;


import kwic.filters.IO;

import java.io.IOException;
import java.util.Scanner;

public class Main { // reading and writing of user input
    public static void main(String[] args) throws IOException {
        String manualListFileName = args[0];
        IO.OutFilter out = IO.newSystemOutFilter();
        app(manualListFileName, out);
    }

    public static void app(String manualListFileName, IO.OutFilter out) {
        Librarian librarian = new Librarian();
        try {
            librarian.processListOfFileNames(manualListFileName);
        } catch (IOException error) {
            out.write(error.getMessage());
            return;
        }
        Scanner in = new Scanner(System.in);
        String query;
        while (true) {
            query = in.nextLine();
            if (query.equals("q")) {
                break;
            }
            librarian.userQueries(query);
            String output = librarian.canIHaveFormattedHistory();
            out.write(output);
        }
        out.write("execution ends");
    }

}
