package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\ru\\javawebinar\\basejava\\folder", new DataStreamSerializer()));
    }
}
