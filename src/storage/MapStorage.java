package storage;

import model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean resumeIsExist(String searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void saveToStorage(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void deleteFromStorage(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void updateToStorage(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected Resume getFromStorage(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected List<Resume> getCopyStorage() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
