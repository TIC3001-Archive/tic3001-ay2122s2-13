package kwic.pipeline;

import kwic.filters.Adapter;
import kwic.filters.IO;
import kwic.filters.Selector;
import kwic.filters.Transformer;

import java.io.IOException;
import java.util.ArrayList;

import static kwic.filters.IO.read;

public class Pipeline {


    public static ArrayList<String> generateEndOfLineDelimitedWordListFromFileName(String path) throws IOException {
        return Adapter.iterateKeywords(read(path));
    }

    // Takes in filenames 1) containing titles 2) ignored keywords 3) required keywords
    public static Selector.SelectionFilter newFilterRequiringDelimitedKeywordsFromFileName(String path) throws IOException {

        return Selector.newFilterRequire((generateEndOfLineDelimitedWordListFromFileName(path)));
    }
    public static void generateAssignment2(String[] args) throws IOException {
        String pathTitle = args[0];
        String pathIgnore = args[1];
        String pathRequired = args[2];

        Selector.SelectionFilter fIgnore = Selector.newFilterIgnore(generateEndOfLineDelimitedWordListFromFileName(pathIgnore));
        Selector.SelectionFilter fRequire = newFilterRequiringDelimitedKeywordsFromFileName(pathRequired);
        IO.OutFilter pWriter = IO.newWriteToFileOutFilter(pathTitle.replace(".txt", "-output.txt"));

        // outer function takes in output of the inner function
        pWriter.write(Adapter.stringify(fRequire.filter(fIgnore.filter(Transformer.lexi((Transformer.shift(Adapter.iterateTitles(read(pathTitle)))))))));
    }


    public static ArrayList<ArrayList<String>> generateConcordance (String filename) throws IOException {
        return (Transformer.lexi((Transformer.shift(Adapter.iterateTitles(read(filename))))));
    }

}
