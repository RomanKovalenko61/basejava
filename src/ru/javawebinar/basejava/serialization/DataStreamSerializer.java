package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeMap(dos, contacts);
            Map<SectionType, Section> sections = resume.getSections();
            writeMap(dos, sections);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            ListSection achievement = new ListSection();
            ListSection qualification = new ListSection();
            Organization organization = new Organization();

            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String section = dis.readUTF();
                switch (section) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        resume.addSections(SectionType.valueOf(section), new TextSection(dis.readUTF()));
                        break;
                    case ("ACHIEVEMENT"):
                        resume.addSections(SectionType.valueOf(section), readListSection(dis, achievement));
                        break;
                    case ("QUALIFICATIONS"):
                        resume.addSections(SectionType.valueOf(section), readListSection(dis, qualification));
                        break;
                    case ("EDUCATION"):
                    case ("EXPERIENCE"):
                        readOrganizationSection(dis, resume, section);
                        break;
                }
            }
            return resume;
        }
    }

    private void writeMap(DataOutputStream dos, Map<?, ?> map) throws IOException {
        dos.writeInt(map.size());
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            dos.writeUTF(entry.getKey().toString());
            dos.writeUTF(entry.getValue().toString());
        }
    }

    private ListSection readListSection(DataInputStream dis, ListSection list) throws IOException {
        list.addNoteToList(dis.readUTF());
        return list;
    }

    private void readOrganizationSection(DataInputStream dis, Resume resume, String section) throws IOException {
        String str = dis.readUTF();
        String[] strings = str.split("\n\n");

        for (String org : strings) {
            String tempStr = org.replaceAll("\n|\t| : | ", " ");
            String[] tempArr = tempStr.split(" ");
            Organization organization = new Organization(tempArr[0], "");
            int cursor = 1;
            while (cursor < tempArr.length) {
                LocalDate start = LocalDate.parse(tempArr[cursor++]);
                LocalDate end = LocalDate.parse(tempArr[cursor++]);
                String descrypt = tempArr[cursor++];
                organization.addNoteToPosition(start, end, descrypt);
            }
            resume.addSections(SectionType.valueOf(section), new OrganizationSection(organization));
        }
    }
}
