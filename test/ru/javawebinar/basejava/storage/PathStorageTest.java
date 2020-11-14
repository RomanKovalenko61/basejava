package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new ObjectStreamSerializer()));
    }
}