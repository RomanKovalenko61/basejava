package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {

    private final List<Resume> storage = new ArrayList<>();

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
    protected boolean resumeIsExist(Integer searchKey) {
        return searchKey >= 0;
    }

    @Override
    protected void saveToStorage(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteFromStorage(Integer searchKey) {
        storage.remove(searchKey.intValue());
    }

    @Override
    protected void updateToStorage(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected List<Resume> getCopyStorage() {
        return new ArrayList<>(storage);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
