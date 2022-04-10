package kwic;


import kwic.filters.Selector;
import kwic.pipeline.Pipeline;

import java.io.IOException;
import java.util.*;

public class Librarian {
    TreeSet<Manual> manuals = new TreeSet<Manual>(Comparator.comparing(m -> m.getName().toLowerCase(Locale.ROOT)));
    public Librarian() {
    }

    private static String manualNameFromFileName(String manualFileName){
        return manualFileName.replace(".txt","");
    }

    public void processListOfFileNames (String manualListFileName) throws IOException {
        ArrayList<String> manualFileNames = Pipeline.generateEndOfLineDelimitedWordListFromFileName(manualListFileName);
        for(String manualFileName : manualFileNames){
            String name = manualNameFromFileName(manualFileName);
            ArrayList<ArrayList<String >> concordance = Pipeline.generateConcordance(manualFileName);
            Manual manual = new Manual(name,concordance);
            manuals.add(manual);
        }
    }

    public void userQueries(String query){
        Selector.SelectionFilter fRequire = Selector.newFilterRequire(new ArrayList<String>(Arrays.asList(query)));

        for(Manual m :manuals){
            ArrayList<ArrayList<String>> concordance = m.getConcordance();
            ArrayList<ArrayList<String>> result = fRequire.filter(concordance);
            m.recordSearchResult(result);


        }
    }

    public String canIHaveFormattedHistory( ){
        return "";
    }


}
