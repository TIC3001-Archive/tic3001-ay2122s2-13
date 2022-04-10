package kwic;

import com.sun.source.tree.Tree;

import java.util.*;


public class Manual {

    private final ArrayList<ArrayList<String>> concordance;
    private final TreeMap<String, Integer> history = new TreeMap<String, Integer>(new StringComparator());
    private final String name;

    public Manual(String name, ArrayList<ArrayList<String>> concordance) { // we pass name and concordance during construction as they are final.
        this.concordance = concordance;
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void recordSearchResult(ArrayList<ArrayList<String>> result) {
        for (ArrayList<String> words : result) {
            String title = String.join(" ", words);
            if (history.containsKey(title)) {
                history.put(title, history.get(title) + 1);
            } else {
                history.put(title, 1);
            }
        }
    }

    public ArrayList<ArrayList<String>> getConcordance() {
        return concordance;
    }

    public TreeMap<String, Integer> getHistory() {
        return history;
    }


}
