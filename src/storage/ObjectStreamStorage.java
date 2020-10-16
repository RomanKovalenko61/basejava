package storage;

import java.io.File;

public class ObjectStreamStorage extends AbstractFileStorage {

    public ObjectStreamStorage(File directory, Serializator serializator) {
        super(directory, serializator);
    }

    public void setSerializator(Serializator serializator) {
        this.serializator = serializator;
    }
}


