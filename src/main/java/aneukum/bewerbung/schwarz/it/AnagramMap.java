package aneukum.bewerbung.schwarz.it;

import java.io.*;
import java.util.*;

/**
 * Stores the lines of a {@link Reader} in a map.
 * Lines that are anagrams of each other are put in the same map value.
 */
public final class AnagramMap {

    private final HashMap<AnagramKey, List<String>> anagramToStrings = new HashMap<>();

    public AnagramMap(Reader reader) throws IOException{
        BufferedReader inputStream = new BufferedReader(reader);
        String line;
        while((line = inputStream.readLine()) != null){
            AnagramKey anagramKey = new AnagramKey(line);
            anagramToStrings.putIfAbsent(anagramKey, new ArrayList<>());
            anagramToStrings.get(anagramKey).add(line);
        }
    }

    public void write(Writer writer) throws IOException {
        for(List<String> lineList : anagramToStrings.values()){
            StringBuilder builder = new StringBuilder();
            if(lineList.size() > 1){
                for(String line: lineList){
                    builder.append(line);
                    builder.append(" ");
                }
                builder.append("\n");
            }
            writer.write(builder.toString());
        }
    }
}
