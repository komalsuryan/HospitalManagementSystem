package org.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class FileIO {
    private static final String PATH = System.getProperty("user.home") + "/.hms/";
    public static String readFile(String fileName) throws FileNotFoundException {
        // read file
        File file = new File(PATH + fileName + ".json");
        JsonReader reader = new JsonReader(new FileReader(file));
        return reader.toString();
    }

    public static void writeFile(String fileName, String contents) throws IOException {
        // write to file
        FileWriter file = new FileWriter(PATH + fileName + ".json");
        // write the contents string to the file overwriting the previous contents
        new Gson().toJson(contents, file);
    }
}
