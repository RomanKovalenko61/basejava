package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> section = resume.getSections();
            dos.writeInt(section.size());
            int sizeSection;
            for (Map.Entry<SectionType, Section> entry : section.entrySet()) {
                String name = entry.getKey().name();
                dos.writeUTF(name);
                switch (name) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        TextSection text = (TextSection) entry.getValue();
                        dos.writeUTF(name);
                        dos.writeUTF(text.getText());
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        ListSection listSection = (ListSection) entry.getValue();
                        sizeSection = listSection.getSize();
                        dos.writeInt(sizeSection);
                        for (int i = 0; i < sizeSection; i++) {
                            dos.writeUTF(listSection.getNote(i));
                        }
                        break;
                    case ("EDUCATION"):
                    case ("EXPERIENCE"):
                        int listSize;
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        sizeSection = organizationSection.getSize();
                        dos.writeInt(sizeSection);
                        for (int i = 0; i < sizeSection; i++) {
                            Organization org = organizationSection.getOrganization(i);
                            dos.writeUTF(org.getPlace().getTitle()); // Link
                            listSize = org.getSize(); // size list<Position> in Organization
                            dos.writeInt(listSize);

                            for (int j = 0; j < listSize; j++) {
                                Organization.Position position = org.getPosition(j);
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getDescription());
                            }
                        }
                        break;
                }
            }
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

            size = dis.readInt();

            for (int i = 0; i < size; i++) {
                String name;
                int sizeSection;
                name = dis.readUTF();
                switch (name) {
                    case ("PERSONAL"):
                    case ("OBJECTIVE"):
                        resume.addSections(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        ListSection listSection = new ListSection();
                        sizeSection = dis.readInt();
                        for (int j = 0; j < sizeSection; j++) {
                            listSection.addNoteToList(dis.readUTF());
                        }
                        resume.addSections(SectionType.valueOf(name), listSection);
                        break;

                    case ("EDUCATION"):
                    case ("EXPERIENCE"):
                        int sizeList = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(sizeList);
                        for (int j = 0; j < sizeList; j++) {
                            Organization org = new Organization(dis.readUTF(), "");
                            int listSize = dis.readInt();
                            for (int k = 0; k < listSize; k++) {
                                org.addNoteToPosition(LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF());
                            }
                            organizations.add(org);
                        }
                        resume.addSections(SectionType.valueOf(name), new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }
}
