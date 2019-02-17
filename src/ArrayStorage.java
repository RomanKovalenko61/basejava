/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    static int count = 0;

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
        count = 0;
    }

    void save(Resume r) {
        boolean check = true;
        String presentName = r.uuid;
        String checkName;
            for (int i = 0; i < count; i++) {
                checkName = storage[i].uuid;
                if (presentName.equals(checkName)) {
                    check = false;
                }
            }
        if (check) {
            storage[count] = r;
            count++;
        }

    }

    Resume get(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int cursor = -1;
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                cursor = i;
                break;
            }
        }
        for (int i = cursor; i < size() - 1; i++) {
            storage[i] = storage[i + 1];
        }
        count--;

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] storageWithoutNull = new Resume[count];
        for (int i = 0; i < size(); i++ ) {
            storageWithoutNull[i] = storage[i];
        }
        return storageWithoutNull;
    }

    int size() {
        return count;
    }
}
