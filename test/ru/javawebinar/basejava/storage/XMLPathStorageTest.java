package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.serialization.XMLStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {

    public XMLPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.toString(), new XMLStreamSerializer()));
    }
}