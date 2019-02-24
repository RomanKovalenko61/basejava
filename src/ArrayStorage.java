import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            int index = getIndex(resume.getUuid());
            if (index == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume is exists");
            }
        } else {
            System.out.println("Attention!!! Storage is overflow");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index > -1 && index < storage.length) {
            return storage[index];
        } else {
            System.out.println("Resume is not found for get");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index > -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume is not found for delete");
        }
    }

    private int getIndex(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return index;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
        } else {
            System.out.println("Resume is not found for update");
        }
    }
}
