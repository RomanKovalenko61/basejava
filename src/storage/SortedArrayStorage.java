package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void saveToArray(int index, Resume resume) {
        int tempIndex = -(index + 1);
        if (tempIndex < size) {
            System.arraycopy(storage, tempIndex, storage, tempIndex + 1, size - tempIndex);
        }
        storage[tempIndex] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        int mov = size - index - 1;
        if (mov > 0) {
            System.arraycopy(storage, index + 1, storage, index, mov);
        }
    }
}
