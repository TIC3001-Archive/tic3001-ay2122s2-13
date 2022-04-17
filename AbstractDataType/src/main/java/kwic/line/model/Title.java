package kwic.line.model;


import kwic.line.aggregator.Lines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Root class for tasks
 */
public class Title {


    public String origin;
    private ArrayList<String> words;

    private Lines concordance = null;

    protected Title() {
    }

    public Title(String line) {
        this.origin = line;
        this.words = new ArrayList<>(Arrays.asList(line.split(" ")));
    }

    public Title(ArrayList<String> words) {
        this.words = words;
        this.origin = String.join(" ", words);
    }


    public Lines concordize() {
        if (concordance == null) {
            concordance = new Lines();
            int length = words.size();
            for (int i = 0; i < length; i++) {
                ArrayList<String> _words = new ArrayList<String>();
                for (int j = 0; j < length; j++) {
                    _words.add(words.get((i + j) % length));
                }
                concordance.addLine(new Title(_words));
            }
        }
        return concordance;
    }

    public static int comparatorTitle(Line line1, Line line2) {
        return line1.origin.toLowerCase(Locale.ROOT).compareTo(line2.origin.toLowerCase(Locale.ROOT));
    }

    public void print() {
        System.out.println(origin);
    }

}
