package kwic;

import kwic.line.aggregator.Lines;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        String sourcePathString = args[0];
        ArrayList<String> sourceLines = new Reader(sourcePathString).getContent();

        Lines lines = Lines.fromStrings(sourceLines);

        Lines concordance = lines.concordize();

        Lines sorted = concordance.getSorted();

        String outputPathString = sourcePathString.replace(".txt", "-output.txt");
        Writer writer = new Writer(outputPathString);
        writer.writeAndClose(sorted.toString());
    }


}
