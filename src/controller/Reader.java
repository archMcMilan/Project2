package controller;

import java.io.FileReader;
import java.io.IOException;

/**
 * interface contains readFile() method that read information from file
 */
interface Reader {

    static String readFile(String path) throws IOException {

        FileReader fr = new FileReader(path);
        StringBuilder buffer = new StringBuilder();

        int symbol = 0;

        symbol = fr.read();
        while (symbol != -1) {
            symbol = fr.read();
            buffer.append((char) symbol);
        }
        fr.close();
        return buffer.toString();
    }
}
