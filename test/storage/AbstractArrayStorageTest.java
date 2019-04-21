package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AbstractArrayStorageTest {

    Storage storage;
    private Resume UUID_1 = new Resume("uuid1","Alex");
    private Resume UUID_2 = new Resume("uuid2","Boris");
    private Resume UUID_3 = new Resume("uuid3","Tanya");
    Resume DUMMY = new Resume("uuid4","dummy");

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(UUID_1);
        storage.save(UUID_2);
        storage.save(UUID_3);
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
        Assert.assertEquals(DUMMY, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("uuid1");
        Assert.assertEquals(2, storage.size());
        storage.get("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid4");
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("uuid4");
    }

    @Test
    public void update() {
        UUID_2.setFullName("Boris Blade");
        storage.update(UUID_2);
        Assert.assertSame(UUID_2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(DUMMY);
    }

    @Test
    public void getAllSorted() {
        List<Resume> RESUMES = Arrays.asList(UUID_1, UUID_2, UUID_3);
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(RESUMES, allResume);
    }
}