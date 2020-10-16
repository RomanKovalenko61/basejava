package storage;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String dir, Serializator serializator) {
        super(dir, serializator);
    }

    public void setSerializator(Serializator serializator) {
        this.serializator = serializator;
    }
}
