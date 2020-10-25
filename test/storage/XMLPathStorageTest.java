package storage;

import serialization.XMLStreamSerializer;

public class XMLPathStorageTest extends AbstractStorageTest {

    public XMLPathStorageTest() {
        super(new PathStorage("C:\\Users\\Roman\\basejava\\src\\folder", new XMLStreamSerializer()));
    }
}