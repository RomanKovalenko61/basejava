package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveToArray(int index, Resume resume) {
        int tempIndex = -(index + 1);
        if (tempIndex < size) {
            System.arraycopy(storage, tempIndex, storage, tempIndex + 1, size - tempIndex);
        }
        storage[tempIndex] = resume;
        size++;
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[size - 1] = null;
        size--;
    }
}
