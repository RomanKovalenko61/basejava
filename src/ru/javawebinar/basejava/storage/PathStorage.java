package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.serialization.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {

    private final Path directory;

    Serializer serializer;

    protected PathStorage(String dir, Serializer serializer) {
        this.directory = Paths.get(dir);
        this.serializer = serializer;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " directory is not directory or is not writable");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
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
            serializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", path.toString(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error ", path.toString(), e);
        }
    }

    @Override
    protected List<Resume> getCopyStorage() {
        return getStream().map(this::getFromStorage).collect(Collectors.toList());
    }

    @Override
    public void clear() {
        getStream().forEach(this::deleteFromStorage);
    }

    @Override
    public int size() {
        return (int) getStream().count();
    }

    private Stream<Path> getStream() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Couldn't get Stream<Path> ", null);
        }
    }

    public void setSerializator(Serializer serializer) {
        this.serializer = serializer;
    }
}
