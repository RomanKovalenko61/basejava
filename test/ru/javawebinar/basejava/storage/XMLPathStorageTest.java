package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.XMLStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {

    public XMLPathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\ru\\javawebinar\\basejava\\folder", new XMLStreamSerializer()));
    }
}