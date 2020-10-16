package storage;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage("C:\\Users\\Roman\\basejava\\folder2", new ObjectStreamSerializator()));
    }
}