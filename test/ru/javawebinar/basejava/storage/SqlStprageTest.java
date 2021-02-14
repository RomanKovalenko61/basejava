package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;

public class SqlStprageTest extends AbstractStorageTest {

    public SqlStprageTest() {
        super(new SQLStorage(Config.get().getDbURL(), Config.get().getDbUser(), Config.get().getDbPassword()));
    }
}
