package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\ru\\javawebinar\\basejava\\folder", new JsonStreamSerializer()));
    }
}
