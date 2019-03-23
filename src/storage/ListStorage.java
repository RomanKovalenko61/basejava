package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected static final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index <= -1) {
            storage.add(resume);
        } else {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            return storage.get(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    private int getIndex(String uuid) {
        return storage.indexOf(new Resume(uuid));
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

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage.remove(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            storage.add(index, r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }
}
