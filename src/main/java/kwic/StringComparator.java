package kwic;

import java.util.Comparator;
import java.util.Locale;


public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        // a
        // abc

        // abcd
        // ABcd
        // AbcD


        //
        int is = a.toLowerCase(Locale.ROOT).compareTo(b.toLowerCase(Locale.ROOT));
        if (is == 0) { // lowered a and lowered b are same but a and b should have a strict ordering.
            return b.compareTo(a);
        } else {
            return is;

        }

    }
}
