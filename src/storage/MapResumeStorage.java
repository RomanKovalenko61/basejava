package storage;

import model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {

    private static final Map<Resume, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(new Resume(uuid));
    }

    @Override
    protected boolean resumeIsExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveToStorage(Object searchKey, Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        storage.remove((Resume) searchKey);
    }

    @Override
    protected void updateToStorage(Object searchKey, Resume resume) {
        storage.put(resume, resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return storage.get((Resume) searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<>(storage.values());
        Collections.sort(sortedList);
        return sortedList;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
