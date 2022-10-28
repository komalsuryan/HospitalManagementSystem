package org.example;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {
    private static final String PATH = System.getProperty("user.home") + "/.hms/";
    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(PATH + fileName + ".json")));
    }

    public static void writeFile(String fileName, String contents) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH + fileName + ".json");
        fileWriter.write(contents);
        fileWriter.close();
    }
}
