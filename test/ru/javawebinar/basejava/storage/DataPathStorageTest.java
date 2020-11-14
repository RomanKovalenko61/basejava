package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new DataStreamSerializer()));
    }
}
