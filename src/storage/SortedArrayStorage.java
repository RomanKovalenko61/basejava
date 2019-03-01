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
    protected void sort() {
        insertSort();
    }

    private void insertSort() {
        for (int i = 1; i < size; i++) {
            Resume current = storage[i];
            int previousIndex = i - 1;
            while (previousIndex > -1 && storage[previousIndex].compareTo(storage[previousIndex + 1]) > 0) {
                storage[previousIndex + 1] = storage[previousIndex];
                storage[previousIndex] = current;
                previousIndex--;
            }
        }
    }
}
