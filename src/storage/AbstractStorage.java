package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(ArrayStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean resumeIsExist(SK searchKey);

    protected abstract void saveToStorage(SK searchKey, Resume resume);

    protected abstract void deleteFromStorage(SK searchKey);

    protected abstract void updateToStorage(SK searchKey, Resume resume);

    protected abstract Resume getFromStorage(SK searchKey);

    protected abstract List<Resume> getCopyStorage();

    @Override
    public void save(Resume resume) {
        LOG.info("save " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        saveToStorage(searchKey, resume);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        deleteFromStorage(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    @Override
    public void update(Resume resume) {
        LOG.info("update " + resume);
        SK searchKey = getExistSearchKey(resume.getUuid());
        updateToStorage(searchKey, resume);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("calling getAllSorted");
        List<Resume> listSorted = getCopyStorage();
        Collections.sort(listSorted);
        return listSorted;
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!resumeIsExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (resumeIsExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
