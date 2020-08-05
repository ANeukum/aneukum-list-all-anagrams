package aneukum.bewerbung.lidl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

        URL url = Main.class.getClassLoader().getResource("sample.txt");
        if(url == null) throw new FileNotFoundException();

        FileReader fileReader = new FileReader(url.getFile());
        AnagramMap anagramMap = new AnagramMap(fileReader);
        System.out.println(anagramMap.toString());
    }

}
