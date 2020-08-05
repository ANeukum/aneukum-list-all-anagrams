package aneukum.bewerbung.lidl;

import java.io.*;
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
            BufferedWriter bufferedWriter = new BufferedWriter(outStream);
            anagramMap.write(bufferedWriter);
            bufferedWriter.flush();
        }
    }

}
