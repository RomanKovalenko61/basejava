package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XMLParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XMLStreamSerializer implements Serializer {
    private XMLParser xmlParser;

    public XMLStreamSerializer() {
        this.xmlParser = new XMLParser(
                Resume.class, Organization.class, OrganizationSection.class,
                Link.class, ListSection.class, TextSection.class, Organization.Position.class);
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer w = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            xmlParser.marshall(resume, w);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return xmlParser.unmarshall(r);
        }
    }
}
