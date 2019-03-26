package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void saveToStorage(int index, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveToArray(index, resume);
        } else {
            throw new StorageException("Attention!!! Storage is overflow", resume.getUuid());
        }
    }

    @Override
    protected void updateToStorage(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume getFromStorage(int index) {
        return storage[index];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void saveToArray(int index, Resume resume);
}
