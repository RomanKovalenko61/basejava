package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;

public interface Serializator {

    void doWrite(Resume resume, OutputStream os) throws IOException;

    Resume doRead(InputStream is);
}
