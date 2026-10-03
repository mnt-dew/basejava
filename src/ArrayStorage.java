import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
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
        int index = indexOfUUID(uuid);
        return index != -1 ? storage[index] : null;
    }

    void delete(String uuid) {
        int index = indexOfUUID(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int indexOfUUID(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
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
