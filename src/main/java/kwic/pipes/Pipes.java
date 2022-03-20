package kwic.pipes;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;

interface FilterInterface {
    ArrayList<ArrayList<String>> filter (ArrayList<ArrayList<String>> source);
}
public class Pipes {

    public static ArrayList<ArrayList<String>> SHIFT (ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> shiftedLinesAll = new ArrayList<>();
        for(ArrayList<String> words: lines){
            ArrayList<ArrayList<String>> thisShiftedLines = new ArrayList<>();
            int length = words.size();
            for (int i = 0; i < length; i++) {
                ArrayList<String> _words = new ArrayList<>();
                for (int j = 0; j < length; j++) {
                    _words.add(words.get((i + j) % length));
                }
                thisShiftedLines.add(_words);
            }
            shiftedLinesAll.addAll(thisShiftedLines);
        }
        return shiftedLinesAll;
    }


    public static ArrayList<ArrayList<String>> LEXI (ArrayList<ArrayList<String>> lines) {
        ArrayList<ArrayList<String>> alphabetized = new ArrayList<>();
        for(ArrayList<String> words: lines){
            alphabetized.add(words);
        }

        alphabetized.sort((ArrayList<String> a, ArrayList<String> b) -> String.join(" ",a).toLowerCase(Locale.ROOT).compareTo(String.join(" ",b).toLowerCase(Locale.ROOT)));
        return alphabetized;
    }
    public static FilterInterface NEW_FILTER(ArrayList<String> ignoreList){

        final HashSet<String> ignoreSet = new HashSet<>();

        for(String ignore: ignoreList){
            ignoreSet.add(ignore.toLowerCase(Locale.ROOT));
        }

        return new FilterInterface() {
            public ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> lines) {
                ArrayList<ArrayList<String>> filtered = new ArrayList<>();
                for(ArrayList<String> words: lines){
                    if(!ignoreSet.contains(words.get(0).toLowerCase(Locale.ROOT))){
                        filtered.add(words);
                    };
                }
                return filtered;
            }
        };
    }
}
