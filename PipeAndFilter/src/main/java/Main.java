import java.io.*;
import java.util.*;
import java.io.File;

public class Main {
    static String[][] parseFile(String filepath) throws FileNotFoundException { //parse strings from input file into array of array
        File file = new File(filepath);
        Scanner scan = new Scanner(file);

        ArrayList<String> ar = new ArrayList<>();
        while (scan.hasNextLine()) {
            ar.add(scan.nextLine());
        }

        String[][] array = new String[ar.size()][];
        for (int i = 0; i < ar.size(); i++) {
            array[i] = ar.get(i).split(" ");
        }
        return array;
    }

    static ArrayList<ArrayList<String>> rearrange(String[][] arrays) { //rearrange each line item by shifting right n times for n words in sentence
        int SIZE = arrays.length;
        ArrayList<ArrayList<String>> semi = new ArrayList<>();
        for (int q = 0; q < SIZE; q++) {
            String[] arr = arrays[q];

            for (int a = 0; a < arrays[q].length; a++) {
                int shift = a;
                int size = arrays[q].length;
                String[] temp = new String[size];
                int j = 0;

                while (shift >= size) {
                    shift = shift - size;
                }

                for (int i = 0; i < size; i++) {
                    if (size - shift + i >= size) {
                        temp[i] = arr[j];
                        j = j + 1;
                    } else {
                        temp[i] = arr[size - shift + i];
                    }
                }
                ArrayList<String> current = new ArrayList<>(Arrays.asList(temp));
                semi.add(current);
            }
        } return semi;
    }

    static ArrayList<ArrayList<String>> lexi (ArrayList<ArrayList<String>> arrays) { //sort it lexicographically
        arrays.sort(Comparator.comparing(a -> String.join(" ",a).toLowerCase()));
        return arrays;
    }

    static void write (String filename, ArrayList<ArrayList<String>> in) throws IOException { //write output file
        FileWriter writer = new FileWriter(filename);
        for (ArrayList<String> element : in) {
            for (String e : element)
            {
                writer.write(e);
                writer.write(" ");
            }
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        String input_path = args[0];
        String output_path = input_path.replace(".txt", "-output.txt");

        write(output_path,lexi(rearrange(parseFile(input_path))));
    }
}
