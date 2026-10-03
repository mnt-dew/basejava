import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int idx = 0; idx < size; idx++) {
            storage[idx] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Хранилище резюме заполнено!");
            return;
        }
        if (indexOfUUID(resume.uuid) != -1) {
            System.out.println("Резюме с таким uuid уже существует!");
        } else {
            storage[size++] = resume;
        }
    }

    Resume get(String uuid) {
        int idx = indexOfUUID(uuid);
        return idx != -1 ? storage[idx] : null;
    }

    void delete(String uuid) {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int indexOfUUID(String uuid) {
        for (int idx = 0; idx < size; idx++) {
            if (storage[idx].uuid.equals(uuid)) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
