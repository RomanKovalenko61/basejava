package storage;

import exception.StorageException;
import model.Resume;

import java.io.*;
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
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO Exception", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    protected void deleteFromStorage(File file) {
        file.delete();
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
        try(FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            return (Resume) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<Resume> getCopyStorage() {
        return null;
    }

    @Override
    public void clear() {
        //TODO: delete all files in directory
    }

    @Override
    public int size() {
        return 0; // TODO: how much files in directory https://stackoverflow.com/questions/1844688/how-to-read-all-files-in-a-folder-from-java)
    }
}
