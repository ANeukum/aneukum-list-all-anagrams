package aneukum.bewerbung.lidl;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramMapTest {

    @Test
    public void putsAnagramsOnSeparateLines() throws IOException {
        StringReader stringReader = new StringReader("restful\nevil\nfluster\nvile\nno");
        AnagramMap anagramMap = new AnagramMap(stringReader);
        StringWriter stringWriter = new StringWriter();
        anagramMap.write(stringWriter);
        String anagrams = stringWriter.toString();

        assertTrue(anagrams.contains("restful fluster \n"));
        assertTrue(anagrams.contains("evil vile \n"));
        assertFalse(anagrams.contains("no"));
    }

    @Test
    public void resultsInEmptyStringIfNoAnagramsProvided() throws IOException {
        AnagramMap anagramMap = new AnagramMap(new StringReader("24\n7\n365"));

        StringWriter stringWriter = new StringWriter();
        anagramMap.write(stringWriter);
        String anagrams = stringWriter.toString();

        assertEquals("", anagrams);
    }

}
