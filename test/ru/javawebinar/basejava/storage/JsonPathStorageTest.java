package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new JsonStreamSerializer()));
    }
}
