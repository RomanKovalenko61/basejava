package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean resumeIsExist(Object searchKey);

    protected abstract void saveToStorage(Object searchKey, Resume resume);

    protected abstract void deleteFromStorage(Object searchKey);

    protected abstract void updateToStorage(Object searchKey, Resume resume);

    protected abstract Resume getFromStorage(Object searchKey);

    @Override
    public void save(Resume resume) {
        Object searchKey = getNotExistSearchKey(resume.getUuid());
        saveToStorage(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        deleteFromStorage(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    @Override
    public void update(Resume resume) {
        Object searchKey = getExistSearchKey(resume.getUuid());
        updateToStorage(searchKey, resume);
    }

    private Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!resumeIsExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (resumeIsExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
