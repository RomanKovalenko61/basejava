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

    private Map<SectionType, Object> sectionTypeMap = new HashMap<>();

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

    public void printResumeContacts() {
        for (ContactType type : ContactType.values()) {
            TextSectionType temp = contactTypeMap.get(type);
            if (!temp.getDescription().equals("")) {
                System.out.println(temp.getText());
            }
        }
    }

    public void printResumeSections() {
        for (SectionType type : SectionType.values()) {
            switch (type) {
                case PERSONAL:
                case OBJECTIVE:
                    TextSectionType textObject = (TextSectionType) sectionTypeMap.get(type);
                    if (!textObject.getDescription().equals("")) {
                        System.out.println(textObject.getText());
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    ListSectionType listObject = (ListSectionType) sectionTypeMap.get(type);
                    System.out.println(listObject.getTitle());
                    for (String element : listObject.getList()) {
                        System.out.println(element);
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    TableSectionType tableObject = (TableSectionType) sectionTypeMap.get(type);
                    System.out.println(tableObject.getTitle());
                    for (TableSectionType.Table element : tableObject.getList()) {
                        System.out.println(element.getPlace());
                        System.out.println(element.getPeriod() + ": " + element.getDescription());
                    }
                    break;
            }
        }
    }
}
