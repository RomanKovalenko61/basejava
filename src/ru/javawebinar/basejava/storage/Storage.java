package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void save(Resume r);

    Resume get(String uuid);

    void delete(String uuid);

    void update(Resume r);

    List<Resume> getAllSorted();

    int size();
}
