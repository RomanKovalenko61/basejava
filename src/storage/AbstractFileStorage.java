package storage;

import exception.StorageException;
import model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not writable/readable");
        }
        this.directory = directory;
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
            if (!file.createNewFile()) {
                File resumeFile = new File(directory, resume.getUuid());
                doWrite(resume, resumeFile);
            }
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @Override
    protected void deleteFromStorage(File file) {
        boolean isDeleted = file.delete();
        if (!isDeleted) {
            throw new StorageException("IO Exception", file.getName());
        }
    }

    @Override
    protected void updateToStorage(File file, Resume resume) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    protected abstract Resume doRead(File file) throws IOException;

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
        for (File entry : Objects.requireNonNull(folderEntries, "wrong path - " + directory)) {
            entry.delete();
        }
        directory.delete();
    }

    @Override
    public int size() {
        File[] folderEntries = getEntryFiles(directory);
        if (folderEntries != null) {
            return folderEntries.length;
        } else {
            return 0;
        }
    }

    private File[] getEntryFiles(File directory) {
        return directory.listFiles();
    }
}
