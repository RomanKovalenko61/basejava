package storage;

import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
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
    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (size == 0) {
                storage[0] = resume;
                size++;
            } else {
                int index = getIndex(resume.getUuid());
                if (index <= -1) {
                    saveToArray(index, resume);
                } else {
                    System.out.println("Resume is exists");
                }
            }
        } else {
            System.out.println("Attention!!! Storage is overflow");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            if (index == 0 && size == 1) {
                storage[0] = null;
                size = 0;
            } else if (index == size - 1 && !(size == 0)) {
                storage[index] = null;
                size--;
            } else {
                deleteFromArray(index);
            }
        } else {
            System.out.println("Resume is not found for delete");
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index <= -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume is not found for update");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected void saveResume(int tempIndex, Resume resume) {
        storage[tempIndex] = resume;
        size++;
    }

    protected void deleteResume() {
        storage[size - 1] = null;
        size--;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveToArray(int index, Resume resume);

    protected abstract void deleteFromArray(int index);
}
