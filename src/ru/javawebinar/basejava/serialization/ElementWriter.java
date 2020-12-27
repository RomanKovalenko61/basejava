package ru.javawebinar.basejava.serialization;


import java.io.IOException;

@FunctionalInterface
public interface ElementWriter<T> {

    void writer(T t) throws IOException;
}
