package aneukum.bewerbung.lidl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramTest {

    @Test
    public void equalsShouldRecognizeAnagrams(){
        Anagram anagramOne = new Anagram("evil");
        Anagram anagramTwo = new Anagram("vile");
        assertEquals(anagramOne, anagramTwo);
        assertEquals(anagramOne.hashCode(), anagramTwo.hashCode());
    }

    @Test
    public void equalsShouldRecognizeNonAnagrams(){
        assertNotEquals(new Anagram("1234"), new Anagram("5678"));
    }

    @Test
    public void shouldBeSurrogateAware(){
        assertNotEquals(
            new Anagram("\ud801\udc01\ud802\udc02"),
            new Anagram("\ud801\udc02\ud802\udc01")
        ); //hash codes are actually equal
    }
}
