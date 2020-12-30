package ru.javawebinar.basejava.serialization;

import java.io.IOException;

@FunctionalInterface
public interface ElementReader {

    void read() throws IOException;
}
