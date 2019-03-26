package storage;

import model.Resume;

import java.util.LinkedList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected static final List<Resume> storage = new LinkedList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    protected int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
    }

    @Override
    protected void saveToStorage(int index, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage.remove(index);
    }

    @Override
    protected void updateToStorage(int index, Resume resume) {
        storage.add(index, resume);
    }

    @Override
    protected Resume getFromStorage(int index) {
        return storage.get(index);
    }

    @Override
    public Resume[] getAll() {
        Resume[] getAllResumes = new Resume[storage.size()];
        return storage.toArray(getAllResumes);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
