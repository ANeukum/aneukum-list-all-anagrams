package aneukum.bewerbung.lidl;

import java.io.*;
import java.util.*;

/**
 * Stores the lines of a {@link Reader} in a map.
 * Lines that are anagrams of each other are put in the same map value.
 */
public final class AnagramMap {

    private final HashMap<Anagram, List<String>> anagramToStrings = new HashMap<>();

    public AnagramMap(Reader reader) throws IOException{
        BufferedReader inputStream = new BufferedReader(reader);
        String line;
        while((line = inputStream.readLine()) != null){
            Anagram anagram = new Anagram(line);
            anagramToStrings.putIfAbsent(anagram, new ArrayList<>());
            anagramToStrings.get(anagram).add(line);
        }
    }

    public void write(Writer writer) throws IOException {
        BufferedWriter outputStream = new BufferedWriter(writer);
        for(List<String> lineList : anagramToStrings.values()){
            StringBuilder builder = new StringBuilder();
            if(lineList.size() > 1){
                for(String line: lineList){
                    builder.append(line);
                    builder.append(" ");
                }
                builder.append("\n");
            }
            outputStream.write(builder.toString());
        }
        outputStream.flush();
    }
}
