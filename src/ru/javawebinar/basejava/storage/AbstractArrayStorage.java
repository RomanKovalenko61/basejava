package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    static final int STORAGE_LIMIT = 10_000;

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
    protected void saveToStorage(Integer searchKey, Resume resume) {
        if (size < STORAGE_LIMIT) {
            saveToArray(searchKey, resume);
            size++;
        } else {
            throw new StorageException("Attention!!! Storage is overflow", resume.getUuid());
        }
    }

    @Override
    protected void deleteFromStorage(Integer searchKey) {
        deleteFromArray(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean resumeIsExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void updateToStorage(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected Resume getFromStorage(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    public List<Resume> getCopyStorage() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }
}
