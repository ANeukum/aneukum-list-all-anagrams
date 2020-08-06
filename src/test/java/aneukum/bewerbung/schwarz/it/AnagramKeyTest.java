package aneukum.bewerbung.schwarz.it;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnagramKeyTest {

    @Test
    public void equalsShouldRecognizeAnagrams(){
        AnagramKey anagramKeyOne = new AnagramKey("evil");
        AnagramKey anagramKeyTwo = new AnagramKey("vile");
        assertEquals(anagramKeyOne, anagramKeyTwo);
        assertEquals(anagramKeyOne.hashCode(), anagramKeyTwo.hashCode());
    }

    @Test
    public void equalsShouldRecognizeNonAnagrams(){
        assertNotEquals(new AnagramKey("1234"), new AnagramKey("5678"));
    }

    @Test
    public void shouldBeSurrogateAware(){
        assertNotEquals(
            new AnagramKey("\ud801\udc01\ud802\udc02"),
            new AnagramKey("\ud801\udc02\ud802\udc01")
        );
        //hash codes are equal
    }
}
