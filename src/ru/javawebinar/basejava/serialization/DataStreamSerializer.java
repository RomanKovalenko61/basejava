package ru.javawebinar.basejava.serialization;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), (entry) -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            Map<SectionType, Section> section = resume.getSections();
            writeCollection(dos, section.entrySet(), (entry) -> {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) entry.getValue()).getListNote(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeCollection(dos, ((OrganizationSection) entry.getValue()).getOrganizations(), org -> {
                            dos.writeUTF(org.getPlace().getTitle());
                            dos.writeUTF(org.getPlace().getUrl());
                            writeCollection(dos, org.getListPosition(), position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContactMap = dis.readInt();
            for (int i = 0; i < sizeContactMap; i++) {
                resume.addContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSectionMap = dis.readInt();

            for (int i = 0; i < sizeSectionMap; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSections(type, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int sizeListSection = dis.readInt();
                        ListSection listSection = new ListSection();
                        for (int j = 0; j < sizeListSection; j++) {
                            listSection.addNoteToList(dis.readUTF());
                        }
                        resume.addSections(type, listSection);
                        break;

                    case EDUCATION:
                    case EXPERIENCE:
                        int sizeOrganizationList = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(sizeOrganizationList);
                        for (int j = 0; j < sizeOrganizationList; j++) {
                            Organization org = new Organization(dis.readUTF(), dis.readUTF());
                            int positionListSize = dis.readInt();
                            for (int k = 0; k < positionListSize; k++) {
                                org.addNoteToPosition(LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF());
                            }
                            organizations.add(org);
                        }
                        resume.addSections(type, new OrganizationSection(organizations));
                        break;
                }
            }
            return resume;
        }
    }

    public static <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> write) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            write.writer(item);
        }
    }
}
