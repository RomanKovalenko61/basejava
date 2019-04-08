package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    protected static final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean resumeIsExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveToStorage(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteFromStorage(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected void updateToStorage(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume getFromStorage(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Collection resumes = storage.values();
        Resume[] allResumes = new Resume[storage.size()];
        resumes.toArray(allResumes);
        Arrays.sort(allResumes);
        return allResumes;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
