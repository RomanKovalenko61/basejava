package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.ObjectStreamSerializer;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\ru\\javawebinar\\basejava\\folder", new ObjectStreamSerializer()));
    }
}