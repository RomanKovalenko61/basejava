package model;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>, Serializable {

    private static final long serialVersionUID = 588620525657485165L;

    private final String uuid;

    private String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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

    public String getFullName() {
        return fullName;
    }

    public String getContacts(ContactType key) {
        return contacts.get(key);
    }

    public Section getSectionValue(SectionType key) {
        return sections.get(key);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addContacts(ContactType key, String value) {
        contacts.put(key, value);
    }

    public void updateContacts(ContactType key, String value) {
        contacts.put(key, value);
    }

    public void addSections(SectionType key, Section value) {
        sections.put(key, value);
    }

    public void updateSections(SectionType key, Section value) {
        sections.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return "uuid = " + uuid + " fullname = " + fullName + "\n";
    }

    @Override
    public int compareTo(Resume resume) {
        return uuid.compareTo(resume.uuid);
    }
}
