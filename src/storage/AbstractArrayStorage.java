package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void saveToArray(int index, Resume resume);

    protected abstract void deleteFromArray(int index);

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
    protected void saveToStorage(Object searchKey, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveToArray((Integer) searchKey, resume);
            size++;
        } else {
            throw new StorageException("Attention!!! Storage is overflow", resume.getUuid());
        }
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        deleteFromArray((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean resumeIsExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void updateToStorage(Object searchKey, Resume resume) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
