package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStprageTest extends AbstractStorageTest {

    public SqlStprageTest() {
        super(Config.get().getStorage());
    }
}
