package kwic;


import kwic.filters.IO;
import kwic.pipeline.Pipeline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main { // reading and writing of user input
    public static void main(String[] args)throws IOException {
        String manualListFileName = args[0];
        IO.OutFilter out = IO.newSystemOutFilter();

        app(manualListFileName,out);
    }

    public static void app(String manualListFileName, IO.OutFilter out) {
        Librarian librarian = new Librarian();
        try{
            librarian.processListOfFileNames(manualListFileName);
        }catch (IOException error){
            out.write(" Some error ");
            return;
        }
        Scanner in = new Scanner(System.in);
        String query;
        while((query = in.nextLine()) != "q"){
                librarian.userQueries(query);
                String output = librarian.canIHaveFormattedHistory();
        }
        out.write("execution ends");
    }


    public static int newLibarian(ArrayList<String> manualFileNames) {
        return -1;
    }


}
