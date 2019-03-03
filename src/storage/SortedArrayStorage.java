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
        int tempIndex = ~index;
        if (~index < size) {
            System.arraycopy(storage, tempIndex, storage, tempIndex + 1, size + 1);
            storage[tempIndex] = resume;
            System.arraycopy(storage, tempIndex + 1, storage, tempIndex + 1, size + 1);
            size++;
        } else {
            storage[tempIndex] = resume;
            size++;
        }
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1);
        size--;
    }
}
