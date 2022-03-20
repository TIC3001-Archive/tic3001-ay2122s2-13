package kwic;

import kwic.filters.FilterInterface;
import kwic.filters.Filters;
import kwic.kwicio.Writer;
import kwic.kwicio.WriterInterface;


import static kwic.kwicio.Reader.READ;
import static kwic.pipes.Pipes.*;

public class Main {

    public static void main(String pathTitle,String pathIgnore,String pathRequired) throws Exception {
        FilterInterface fIgnore = Filters.NEW_FILTER_IGNORE(TO_ITERABLE_TITLE(READ(pathIgnore)));
        FilterInterface fRequire = Filters.NEW_FILTER_REQUIRE(TO_ITERABLE_TITLE(READ(pathRequired)));
        WriterInterface pWriter = Writer.NEW_WRITE(pathTitle.replace(".txt","-output.txt"));
        pWriter.write(TO_STRING(fRequire.filter( fIgnore.filter((SHIFT(TO_ITERABLE_TITLES(READ(pathTitle))))))));
    }




}
