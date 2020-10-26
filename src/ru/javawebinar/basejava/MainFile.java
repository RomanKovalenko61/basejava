package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("C:\\Users\\Roman\\basejava\\src");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //HW08 print names
        File folder = new File("src");

        System.out.println("This function printing names dirs and files in " + folder.getAbsolutePath());
        printNamesFilesFromFolder(folder);

    }

    public static void printNamesFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : Objects.requireNonNull(folderEntries, "wrong path - " + folder)) {
            if (entry.isDirectory()) {
                System.out.println("DIR : " + entry.getName());
                printNamesFilesFromFolder(entry);
            } else if (entry.getParent().equals(folder.getName())) {
                System.out.println("File - " + entry.getName());
            } else {
                System.out.println("\t + File - " + entry.getName());
            }
        }
    }
}
