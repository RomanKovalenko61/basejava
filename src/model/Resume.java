package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    private Map<ContactType, TextSectionType> contactTypeMap = new HashMap<>();

    private Map<SectionType, Printable> sectionTypeMap = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addContactTypeMap(ContactType key, TextSectionType value) {
        contactTypeMap.put(key, value);
    }

    public void addSectionTypeMap(SectionType key, Printable value) {
        sectionTypeMap.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "uuid = " + uuid + " fullname = " + fullName;
    }

    @Override
    public int compareTo(Resume resume) {
        int result = fullName.compareTo(resume.fullName);
        return result == 0 ? uuid.compareTo(resume.uuid) : result;
    }

    public void printThisResume() {
        System.out.println(this);
        printResumeContacts();
        printResumeSections();
    }

    private void printResumeContacts() {
        for (ContactType type : ContactType.values()) {
            if (contactTypeMap.containsKey(type)) {
                Printable temp = contactTypeMap.get(type);
                temp.print();
            }
        }
    }

    private void printResumeSections() {
        for (SectionType type : SectionType.values()) {
            if (sectionTypeMap.containsKey(type)) {
                Printable temp = sectionTypeMap.get(type);
                temp.print();
            }
        }
    }
}
