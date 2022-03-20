package kwic.pipes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TestShift {


    boolean IS_SAME_LINE (ArrayList<String> expected, ArrayList<String> actual) {
        int size = expected.size();
        if(size != actual.size()){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(expected.get(i) != actual.get(i)){
                return false;
            }
        }
        return true;
    }

    boolean IS_SAME_LINES (ArrayList<ArrayList<String>> expected, ArrayList<ArrayList<String>> actual) {
        int size = expected.size();
        if(size != actual.size()){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(!IS_SAME_LINE(expected.get(i),actual.get(i))){
                return false;
            }
        }
        return true;

    }
    @Test
    void Test_Input_Empty() {

        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<ArrayList<String>> actual = Pipes.SHIFT(input);

        assertTrue(IS_SAME_LINES(input,actual));
    }

}
