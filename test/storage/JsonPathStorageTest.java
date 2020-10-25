package storage;

import serialization.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\folder", new JsonStreamSerializer()));
    }
}
