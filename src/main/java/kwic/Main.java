package kwic;

import kwic.filters.FilterInterface;
import kwic.filters.Filters;
import kwic.kwicio.Writer;
import kwic.kwicio.WriterInterface;

import static kwic.kwicio.Reader.READ;

public class Main {

    public static void main(String[] args) {

        String pathTitle = args[0];
        String pathIgnore = args[1];
        String pathRequired = args[2];

        FilterInterface fIgnore = Filters.NEW_FILTER_IGNORE(Filters.ITERATE_KEYWORDS_LSV(READ(pathIgnore)));
        FilterInterface fRequire = Filters.NEW_FILTER_REQUIRE(Filters.ITERATE_KEYWORDS_LSV(READ(pathRequired)));
        WriterInterface pWriter = Writer.NEW_WRITE(pathTitle.replace(".txt", "-output.txt"));
        pWriter.write(Filters.STRINGIFY(fRequire.filter(fIgnore.filter(Filters.LEXI((Filters.SHIFT(Filters.ITERATE_TITLES(READ(pathTitle)))))))));
    }


}
