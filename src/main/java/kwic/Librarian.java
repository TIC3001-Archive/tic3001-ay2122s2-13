package kwic;


import kwic.filters.Selector;
import kwic.pipeline.Pipeline;

import java.io.IOException;
import java.util.*;

import static kwic.filters.Adapter.toHashSet;

public class Librarian {
    TreeSet<Manual> manuals = new TreeSet<Manual>(Comparator.comparing(m -> m.getName().toLowerCase(Locale.ROOT)));

    public Librarian() {
    }

    public void processListOfFileNames(String manualListFileNamePath) throws IOException {
        ArrayList<String> manualFileNames = Pipeline.listOfFileNamePathToManualFileNamePipeline(manualListFileNamePath);
        for (String manualFileName : manualFileNames) {
            String name = manualFileName;
            ArrayList<ArrayList<String>> concordance = Pipeline.fileNameToConcordancePipeline(manualFileName);
            Manual manual = new Manual(name, concordance);
            manuals.add(manual);
        }
    }

    public void userQueries(String query) {


        Selector.SelectionFilter fRequire = Selector.newRequireFilter(Selector.newRequiredLinePredicate(toHashSet(new ArrayList<String>(List.of(query)))));

        for (Manual m : manuals) {
            ArrayList<ArrayList<String>> concordance = m.getConcordance();
            ArrayList<ArrayList<String>> result = fRequire.filter(concordance);
            m.recordSearchResult(result);
        }
    }

    public String canIHaveFormattedHistory() {
        StringBuilder sb = new StringBuilder();
        for (Manual m : manuals) {
            TreeMap<String, Integer> history = m.getHistory();
            if (history.isEmpty()) {
                break;
            }
            sb.append(m.getName()).append(System.lineSeparator());

            for (Map.Entry<String, Integer>
                    entry : history.entrySet()) {
                String title = entry.getKey();
                Integer occurrence = entry.getValue();

                while (occurrence > 0) {
                    sb.append(title).append(System.lineSeparator());
                    occurrence -= 1;
                }
            }
        }
        return sb.toString();
    }


}
