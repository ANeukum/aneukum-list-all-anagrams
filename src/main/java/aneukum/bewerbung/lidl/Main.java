package aneukum.bewerbung.lidl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

        URL url = Main.class.getClassLoader().getResource("sample.txt");
        if(url == null) throw new FileNotFoundException();

        AnagramMap anagramMap;
        try(FileReader fileReader = new FileReader(url.getFile())){
             anagramMap = new AnagramMap(fileReader);
        }
        try(OutputStreamWriter outStream = new OutputStreamWriter(System.out)){
            anagramMap.write(outStream);
        }
    }

}
