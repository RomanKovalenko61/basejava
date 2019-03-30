package storage;

import static org.junit.Assert.*;

public class MapStorageTest extends AbstractArrayStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }
}