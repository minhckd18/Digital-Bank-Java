package vn.funix.FX09371.java.asm04.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    public static List<List<String>> readFile(String fileName) throws FileNotFoundException {
        List<List<String>> data;
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            data = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] lineData = line.split(COMMA_DELIMITER);
                data.add(new ArrayList<>(Arrays.asList(lineData)));
            }
        }
        return data;
    }
}
