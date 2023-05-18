package vn.funix.FX09371.java.asm04.jtest;

import vn.funix.FX09371.java.asm04.service.TextFileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TextFileServiceTest {

    @org.junit.Test (expected = FileNotFoundException.class)
    public void readFile() throws IOException {
        List<List<String>> readData = TextFileService.readFile("store/store/store/customers.txt");
    }
}