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

        File dir = new File("./src/ru/javawebinar/basejava");
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
        File folder = new File("D:\\DOCUM\\basejava\\src");
        printNamesFilesFromFolder(folder);

    }

    public static void printNamesFilesFromFolder(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : Objects.requireNonNull(folderEntries, "wrong path - " + folder)) {
            if (entry.isDirectory()) {
                printNamesFilesFromFolder(entry);
            } else {
                System.out.println(entry.getName());
            }
        }
    }
}
