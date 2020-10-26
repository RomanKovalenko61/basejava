package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean resumeIsExist(Resume searchKey) {
        return searchKey != null;
    }

    @Override
    protected void saveToStorage(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteFromStorage(Resume searchKey) {
        storage.remove(searchKey.getUuid());
    }

    @Override
    protected void updateToStorage(Resume searchKey, Resume resume) {
        storage.put(searchKey.getUuid(), resume);
    }

    @Override
    protected Resume getFromStorage(Resume searchKey) {
        return searchKey;
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
