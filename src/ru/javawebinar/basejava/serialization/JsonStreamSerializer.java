package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements Serializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer w = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            JsonParser.write(resume, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return JsonParser.read(r, Resume.class);
        }
    }
}
