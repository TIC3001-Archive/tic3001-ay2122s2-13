package kwic;

import kwic.filters.FilterInterface;
import kwic.filters.Filters;
import kwic.kwicio.Writer;
import kwic.kwicio.WriterInterface;


import java.nio.file.Path;

import static kwic.kwicio.Reader.READ;
import static kwic.pipes.Pipes.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String pathTitle = args[0];
        String pathIgnore = args[1];
        String pathRequired = args[2];

        FilterInterface fIgnore = Filters.NEW_FILTER_IGNORE(TO_ITERABLE_KEYWORDS(READ(pathIgnore)));
        FilterInterface fRequire = Filters.NEW_FILTER_REQUIRE(TO_ITERABLE_KEYWORDS(READ(pathRequired)));
        WriterInterface pWriter = Writer.NEW_WRITE(pathTitle.replace(".txt", "-output.txt"));
        pWriter.write(TO_STRING(fRequire.filter(fIgnore.filter(LEXI((SHIFT(TO_ITERABLE_TITLES(READ(pathTitle)))))))));
    }


}
