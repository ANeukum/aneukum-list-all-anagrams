package aneukum.bewerbung.lidl;

import java.util.HashMap;

/**
 * Represents the set of all Strings that are anagrams to a given String.
 * This is achieved by counting code points.
 *
 * Example: "ABCA" is turned into the map
 *  65 -> 2
 *  66 -> 1
 *  67 -> 1
 *
 * This is surrogate aware and O(n).
 */
public final class Anagram {

    private final HashMap<Integer, Integer> codePointCounts = new HashMap<>();

    public Anagram(String string) {
        string.codePoints().forEach(codePoint -> {
            codePointCounts.putIfAbsent(codePoint, 0);
            codePointCounts.put(codePoint, codePointCounts.get(codePoint) + 1);
        });
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Anagram otherAnagram = (Anagram) other;
        return codePointCounts.equals(otherAnagram.codePointCounts);
    }

    @Override
    public int hashCode() {
        return codePointCounts.hashCode();
    }
}
