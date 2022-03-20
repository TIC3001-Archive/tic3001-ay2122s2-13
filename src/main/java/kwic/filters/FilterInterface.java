package kwic.filters;

import java.util.ArrayList;

public interface FilterInterface {
    ArrayList<ArrayList<String>> filter(ArrayList<ArrayList<String>> source);
}
