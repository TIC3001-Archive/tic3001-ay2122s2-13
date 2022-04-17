package kwic;

import kwic.filters.IO;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static final String exitKeyword = "q";

    /**
     * Entry point.
     * Takes as input a command line argument which is the name of a file containing a list of files (one
     * file name with or without path on each line). E.g., These files are text files that contain different
     * sections of a Help manual.
     */
    public static void main(String[] args) throws IOException {
        String manualListFileName = args[0];
        IO.OutFilter out = IO.newSystemOutFilter();
        app(manualListFileName, out);
    }

    /**
     * The application core. It is separated into two parts. The first part [Data loading] loads the concordances in-memory
     * and the second part [Listener] listens to and respond to user queries. Both parts have escape triggers which exits the program.
     *
     * @param manualListFileName input a command line argument which is the name of a file containing a list of files (one
     *                           file name with or without path on each line). E.g., These files are text files that contain different
     *                           sections of a Help manual.
     * @param out                The out filter which buffers content to Write stream.
     *                           Additionally, check this {@link kwic.filters.IO.OutFilter} method for direction based run.
     */
    public static void app(String manualListFileName, IO.OutFilter out) {
        /**
         *
         * Part 1 - Data loading.
         * The librarian will process from the manualListFileNames.
         * If the initial manualListFileName cannot be read, the IOException error, if any, will be buffered.
         * Then, the program will exit.
         */
        Librarian librarian = new Librarian();
        try {
            librarian.processListOfFileNames(manualListFileName);
        } catch (IOException error) {
            out.write(error.getMessage());
            return;
        }

        /* Part 2
         * 2a ) Loop-awaits for input. Upon input, relay to librarian. The librarian will perform the needful processing.
         * 2b ) The standard response will be the whole history of search result grouped by source manual of the result.
         * 2c ) The loop will escape on input of the exit keyword which belongs natively to Main.
         */
        Scanner in = new Scanner(System.in);
        String query;
        while (true) {
            query = in.nextLine();
            if (query.equals(exitKeyword)) {
                break;
            }
            librarian.userQueries(query);
            String output = librarian.canIHaveFormattedHistory();
            out.write(output);
        }
        out.write("execution ends");
    }

}
