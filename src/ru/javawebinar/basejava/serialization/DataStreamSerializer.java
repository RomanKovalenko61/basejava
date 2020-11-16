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

            for (Map.Entry<SectionType, Section> entry : section.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        TextSection text = (TextSection) entry.getValue();
                        dos.writeUTF(text.getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection listSection = (ListSection) entry.getValue();
                        int sizeListSection = listSection.getSize();
                        dos.writeInt(sizeListSection);
                        for (int i = 0; i < sizeListSection; i++) {
                            dos.writeUTF(listSection.getNote(i));
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        int sizeOrganizationSection = organizationSection.getSize();
                        dos.writeInt(sizeOrganizationSection);
                        for (int i = 0; i < sizeOrganizationSection; i++) {
                            Organization org = organizationSection.getOrganization(i);
                            dos.writeUTF(org.getPlace().getTitle());
                            dos.writeUTF(org.getPlace().getUrl());
                            int listPositionSize = org.getListPositionSize();
                            dos.writeInt(listPositionSize);

                            for (int j = 0; j < listPositionSize; j++) {
                                Organization.Position position = org.getPosition(j);
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
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
}
