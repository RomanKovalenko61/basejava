package storage;

import exception.StorageException;
import model.Resume;
import serialization.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private File directory;

    Serializer serializer;

    protected FileStorage(File directory, Serializer serializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not writable/readable");
        }
        this.directory = directory;
        this.serializer = serializer;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean resumeIsExist(File file) {
        return file.exists();
    }

    @Override
    protected void saveToStorage(File file, Resume resume) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), resume.getUuid(), e);
        }
        updateToStorage(file, resume);
    }

    @Override
    protected void deleteFromStorage(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error ", file.getName());
        }
    }

    @Override
    protected void updateToStorage(File file, Resume resume) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error ", file.getName(), e);
        }
    }

    @Override
    protected List<Resume> getCopyStorage() {
        File[] folderEntries = getEntryFiles(directory);
        List<Resume> copyStorage = new ArrayList<>();
        for (File entry : Objects.requireNonNull(folderEntries, "wrong path - " + directory)) {
            copyStorage.add(getFromStorage(entry));
        }
        return copyStorage;
    }

    @Override
    public void clear() {
        File[] folderEntries = getEntryFiles(directory);
        for (File entry : folderEntries) {
            entry.delete();
        }
    }

    @Override
    public int size() {
        File[] folderEntries = getEntryFiles(directory);
        return folderEntries.length;
    }

    public void setSerializator(Serializer serializer) {
        this.serializer = serializer;
    }

    private File[] getEntryFiles(File directory) {
        if (directory != null) {
            return directory.listFiles();
        } else {
            throw new StorageException("directory is null", null);
        }
    }
}
