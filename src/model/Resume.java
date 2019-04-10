package model;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    public Resume(String uuid) {
        this.uuid = uuid;
        fullName = "No name";
    }

    public Resume(String uuid, String fullName) {
        this(uuid);
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode() + fullName.hashCode();
    }

    @Override
    public String toString() {
        return "uuid = " + uuid + " fullname = " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        if (result == 0) {
            uuid.compareTo(o.uuid);
        }
        return result;
    }
}
