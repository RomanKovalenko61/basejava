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

    private static final long serialVersionUID = 6588620525657485165L;
    // Unique identifier
    private final String uuid;

    private String fullName;

    private Map<ContactType, String> contact = new EnumMap<>(ContactType.class);

    private Map<SectionType, Section> section = new EnumMap<>(SectionType.class);

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

    public String getContactTypeMapValue(ContactType key) {
        return contact.get(key);
    }

    public Section getSectionTypeMapValue(SectionType key) {
        return section.get(key);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addContactTypeMap(ContactType key, String value) {
        contact.put(key, value);
    }

    public void updateContactTypeMap(ContactType key, String value) {
        contact.put(key, value);
    }

    public void addSectionTypeMap(SectionType key, Section value) {
        section.put(key, value);
    }

    public void updateSectionTypeMap(SectionType key, Section value) {
        section.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contact.equals(resume.contact)) return false;
        return section.equals(resume.section);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + section.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "uuid = " + uuid + " fullname = " + fullName + "//n" + contact + section;
    }

    @Override
    public int compareTo(Resume resume) {
        int result = fullName.compareTo(resume.fullName);
        return result == 0 ? uuid.compareTo(resume.uuid) : result;
    }
}
