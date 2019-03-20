package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static storage.AbstractArrayStorage.STORAGE_LIMIT;

public class AbstractArrayStorageTest {

    private Storage storage;
    private static final Resume uuid1 = new Resume("uuid1");
    private static final Resume uuid2 = new Resume("uuid2");
    private static final Resume uuid3 = new Resume("uuid3");
    private static final Resume dummy = new Resume("dummy");
    private static final Resume[] all = {uuid1, uuid2, uuid3};

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(uuid1);
        storage.save(uuid2);
        storage.save(uuid3);
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
        storage.save(dummy);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(dummy, storage.get("dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(uuid1);
    }

    @Test(expected = StorageException.class)
    public void saveWithOverflow() throws IllegalAccessException {
        storage.clear();
        try {
            storage.save(uuid1);
            storage.save(uuid2);
            storage.save(uuid3);
        } catch (StorageException e) {
            Assert.fail("Storage is overflow");
        }
        Field field = storage.getClass().getSuperclass().getDeclaredFields()[2];
        field.setAccessible(true);
        field.setInt(storage, STORAGE_LIMIT);
        storage.save(dummy);
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
        Assert.assertEquals(uuid2, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid2");
        Assert.assertEquals(uuid2, storage.get("uuid2"));
        storage.update(r);
        Assert.assertEquals(r, storage.get("uuid2"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(dummy);
    }

    @Test
    public void getAll() {
        Resume[] allResume = storage.getAll();
        Assert.assertArrayEquals(all, allResume);
    }
}