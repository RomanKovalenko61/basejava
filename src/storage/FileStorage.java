package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;

public class FileStorage extends AbstractFileStorage {
    protected FileStorage(File directory) {
        super(directory);
    }

    @Override
    protected void doWrite(Resume resume, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(resume);
        } catch (Exception e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }
}
