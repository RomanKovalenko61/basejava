package storage;

import serialization.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\folder", new ObjectStreamSerializer()));
    }
}