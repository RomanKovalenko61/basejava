package model;

import java.util.EnumMap;
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

    private Map<ContactType, String> contactTypeMap = new EnumMap<>(ContactType.class);

    private Map<SectionType, Section> sectionTypeMap = new EnumMap<>(SectionType.class);

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
        return contactTypeMap.get(key);
    }

    public Section getSectionTypeMapValue(SectionType key) {
        return sectionTypeMap.get(key);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void addContactTypeMap(ContactType key, String value) {
        contactTypeMap.put(key, value);
    }

    public void updateContactTypeMap(ContactType key, String value) {
        contactTypeMap.put(key, value);
    }

    public void addSectionTypeMap(SectionType key, Section value) {
        sectionTypeMap.put(key, value);
    }

    public void updateSectionTypeMap(SectionType key, Section value) {
        sectionTypeMap.put(key, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        if (!fullName.equals(resume.fullName)) return false;
        if (!contactTypeMap.equals(resume.contactTypeMap)) return false;
        return sectionTypeMap.equals(resume.sectionTypeMap);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + contactTypeMap.hashCode();
        result = 31 * result + sectionTypeMap.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "uuid = " + uuid + " fullname = " + fullName + "//n" + contactTypeMap + sectionTypeMap;
    }

    @Override
    public int compareTo(Resume resume) {
        int result = fullName.compareTo(resume.fullName);
        return result == 0 ? uuid.compareTo(resume.uuid) : result;
    }
}
