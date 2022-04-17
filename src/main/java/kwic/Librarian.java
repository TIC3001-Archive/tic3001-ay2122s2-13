package kwic;


import kwic.filters.Selector;
import kwic.pipeline.Pipeline;

import java.io.IOException;
import java.util.*;

public class Librarian {
    TreeSet<Manual> manuals;

    /**
     * The insert ordering and aggregate container of manuals will be defined once in the constructor.
     */
    public Librarian() {
        manuals = new TreeSet<>(Comparator.comparing(m -> m.getName().toLowerCase(Locale.ROOT)));
    }

    /**
     * See in-line comments.
     *
     * @param manualListFileNamePath
     * @throws IOException
     */
    public void processListOfFileNames(String manualListFileNamePath) throws IOException {

        /**
         * Feed manualListFileNamePath to the pipeline which returns the manual file paths.
         */
        ArrayList<String> manualFileNames = Pipeline.listOfFileNamePathToManualFileNamePipeline(manualListFileNamePath);

        /*
         * For each manualFileName, obtain the content (concordance) in the file and create a manual to store its concordance.
         */
        for (String manualFileName : manualFileNames) {
            String name = manualFileName;
            ArrayList<ArrayList<String>> concordance = Pipeline.fileNameToConcordancePipeline(manualFileName);
            Manual manual = new Manual(name, concordance);
            manuals.add(manual);
        }
    }

    /**
     * The result of the query of each manual will be recorded natively.
     * This method does not return the result. Call {@link #canIHaveFormattedHistory()} to get the result.
     *
     * @param query first word of the title.
     */
    public void userQueries(String query) {
        Selector.SelectionFilter fRequire = Selector.newRequireFilter(Selector.newRequiredLineByFirstWordAgainstSingleKeywordPredicate(query));
        for (Manual m : manuals) {
            ArrayList<ArrayList<String>> concordance = m.getConcordance();
            ArrayList<ArrayList<String>> result = fRequire.filter(concordance);
            m.recordSearchResult(result);
        }
    }

    /**
     * For each manual, obtain its history.
     * If history is empty, continue to the next manual.
     * If history contains entries, append manual name and titles recorded in each history.
     * Titles will repeat for each occurrence in the history.
     * For example, if the title is a result of 3 out of a total of 6 queries, it will be appended 3 times.
     *
     * @return formatted history
     */
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
