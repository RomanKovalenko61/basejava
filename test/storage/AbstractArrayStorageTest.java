package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {

    protected Storage storage;
    protected static final Resume UUID_1 = new Resume("uuid1");
    protected static final Resume UUID_2 = new Resume("uuid2");
    protected static final Resume UUID_3 = new Resume("uuid3");
    protected static final Resume DUMMY = new Resume("dummy");
    protected static final Resume[] RESUMES = {UUID_1, UUID_2, UUID_3};

    protected AbstractArrayStorageTest(Storage storage) {
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
        Assert.assertSame(DUMMY, storage.get("dummy"));
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
        storage.delete("dummy");
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid2");
        storage.update(r);
        Assert.assertSame(r, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(DUMMY);
    }

    @Test
    public void getAll() {
        Resume[] allResume = storage.getAll();
        Assert.assertArrayEquals(RESUMES, allResume);
    }
}