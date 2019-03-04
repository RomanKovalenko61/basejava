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
        if (tempIndex < size) {
            System.arraycopy(storage, tempIndex, storage, tempIndex + 1, size - tempIndex);
            saveResume(tempIndex, resume);
        } else {
            saveResume(tempIndex, resume);
        }
    }

    @Override
    protected void deleteFromArray(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        deleteLastResume();
    }
}
