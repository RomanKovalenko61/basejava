package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Users\\Roman\\basejava\\src\\folder");
    Storage storage;
    private final String UUID_1 = "uuid1";
    private final String UUID_2 = "uuid2";
    private final String UUID_3 = "uuid3";
    private final String UUID_4 = "uuid4";
    private Resume RESUME_1 = new Resume(UUID_1, "Alex");
    private Resume RESUME_2 = new Resume(UUID_2, "Boris");
    private Resume RESUME_3 = new Resume(UUID_3, "Tanya");
    Resume DUMMY = new Resume(UUID_4, "dummy");

    AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        storage.save(DUMMY);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(DUMMY, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2, "Boris Blade");
        storage.update(newResume);
        Assert.assertTrue(newResume.equals(RESUME_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(DUMMY);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(resumes);
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(resumes, allResume);
    }
}