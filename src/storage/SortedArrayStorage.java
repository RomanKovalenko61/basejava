package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size < STORAGE_LIMIT) {
            if (size == 0) {
                storage[0] = resume;
                size++;
            } else {
                int index = getIndex(resume.getUuid());
                if (index <= -1) {
                    if (~index < size) {
                        index= ~index;
                        Resume[] tempStorage = new Resume[size];
                        System.arraycopy(storage, index, tempStorage, 0, size);
                        storage[index] = resume;
                        System.arraycopy(tempStorage, 0, storage, index+1, size);
                        size++;
                    } else {
                        storage[~index] = resume;
                        size++;
                    }
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
        if (index > -1) {
            Resume[] tempStorage = new Resume[size];
            System.arraycopy(storage, index+1, tempStorage, 0, size);
            System.arraycopy(tempStorage, 0, storage, index, size);
            size--;
        } else {
            System.out.println("Resume is not found for delete");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
