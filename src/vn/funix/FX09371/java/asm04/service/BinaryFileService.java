package vn.funix.FX09371.java.asm04.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileService<T> {
    public static <T> List<T> readFile(String fileName) throws IOException {
        List<T> objects = new ArrayList<>();
        try (ObjectInputStream file = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(fileName)))) {
            boolean eof = false;
            while (!eof) {
                try {
                    T object = (T) file.readObject();
//                    System.out.println("Read object: " + object);
                    objects.add(object);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (EOFException e) {
//            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public static <T> void writeFile(String fileName, List<T> objects) throws IOException {
        try (ObjectOutputStream file = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (T object : objects) {
//                System.out.println("Write object: " + object);
                file.writeObject(object);
            }
        }
    }
}
