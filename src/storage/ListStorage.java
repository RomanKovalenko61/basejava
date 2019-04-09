package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected static final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean resumeIsExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    @Override
    protected void saveToStorage(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        storage.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected void updateToStorage(Object searchKey, Resume resume) {
        storage.set((Integer) searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return storage.get((Integer) searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<>(storage);
        Collections.sort(sortedList);
        return sortedList;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
