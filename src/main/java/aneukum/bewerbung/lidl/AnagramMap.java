package aneukum.bewerbung.lidl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * Stores the lines of a {@link Reader} in a map.
 * Lines that are anagrams of each other are put in the same map value.
 * ToString() returns each map value of length >1 on its own line.
 */
public final class AnagramMap {

    private final HashMap<Anagram, List<String>> anagramToStrings = new HashMap<>();

    public AnagramMap(Reader reader) throws IOException{
        try(BufferedReader inputStream = new BufferedReader(reader)) {
            String line;
            while((line = inputStream.readLine()) != null){
                Anagram anagram = new Anagram(line);
                anagramToStrings.putIfAbsent(anagram, new ArrayList<>());
                anagramToStrings.get(anagram).add(line);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(List<String> lineList : anagramToStrings.values()){
            if(lineList.size() > 1){
                for(String line: lineList){
                    builder.append(line);
                    builder.append(" ");
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
