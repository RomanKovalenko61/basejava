package storage;

import exception.StorageException;
import model.Resume;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private final Path directory;

    Serializator serializator;

    protected AbstractPathStorage(String dir, Serializator serializator) {
        this.directory = Paths.get(dir);
        this.serializator = serializator;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " directory is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory + "\\" + uuid);
    }

    @Override
    protected boolean resumeIsExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected void saveToStorage(Path path, Resume resume) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path ", path.toString(), e);
        }
        updateToStorage(path, resume);
    }

    @Override
    protected void deleteFromStorage(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error ", path.toString());
        }
    }

    @Override
    protected void updateToStorage(Path path, Resume resume) {
        try {
            serializator.doWrite(resume, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path write error", path.toString(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return serializator.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path read error ", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> getCopyStorage() {
        List<Resume> copy = new ArrayList<>();
        try {
            Files.list(directory).forEach(p -> copy.add(getFromStorage(p)));
        } catch (IOException e) {
            throw new StorageException("Couldn't get copy storage with stream", null, e);
        }
        return copy;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteFromStorage);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.toFile().list()).length;
    }
}
