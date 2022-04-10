package kwic;

import java.util.*;


class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a,String b){
            return a.toLowerCase(Locale.ROOT).compareTo(b.toLowerCase(Locale.ROOT));
        }
}

public class Manual {

    private final ArrayList<ArrayList<String>> concordance;
    private final HashMap<String,Integer> history = new HashMap<String,Integer>((Map<? extends String, ? extends Integer>) new StringComparator());
    private final String name;
    public Manual(String name,ArrayList<ArrayList<String>> concordance) { // we pass name and concordance during construction as they are final.
        this.concordance = concordance;
        this.name = name;
    }

    String getName() {return name;}



    public void recordSearchResult(ArrayList<ArrayList<String>> result){
        for(ArrayList<String> words: result){
            String title = String.join(" ",words);
            if(history.containsKey(title)){
                history.put(title,history.get(title) + 1);
            }else{
                history.put(title,1);
            }
        }
    }
    public ArrayList<ArrayList<String>> getConcordance() {
        return concordance;
    }




}
