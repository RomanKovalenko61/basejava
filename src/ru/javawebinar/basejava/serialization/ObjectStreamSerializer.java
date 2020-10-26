package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream is) {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new StorageException("Error read resume", null, e);
        }
    }
}
