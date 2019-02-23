import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume resume) {
        if (size < 10000) {
            if (checkResumeIsExists(resume) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume is exist! Create another resume");
            }
        } else {
            System.out.println("Attention!!! Massive storage is overflow");
        }
    }

    Resume get(Resume resume) {
        int cursor = checkResumeIsExists(resume);
        if (cursor > -1) {
            return storage[cursor];
        } else {
            System.out.println("Do not get resume! Resume is not found!");
            return null;
        }
    }

    void delete(Resume resume) {
        int cursor = checkResumeIsExists(resume);
        if (cursor > -1) {
            storage[cursor] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Do not delete resume! Resume is not found");
        }
    }

    void update(Resume resume) {
        int cursor = checkResumeIsExists(resume);
        if (cursor > -1) {
            storage[cursor] = resume;
        } else {
            System.out.println("Do not update resume! Resume is not found");
        }
    }

    int checkResumeIsExists(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(resume.uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageWithoutNull = new Resume[size];
        return storageWithoutNull = Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
