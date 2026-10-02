import java.util.Arrays;
import java.util.UUID;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    private int indexOfUUID(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    void clear() {
        for (int i = 0; i < size; i++) storage[i] = null;
        size = 0;
    }

    void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Хранилище резюме заполнено!");
            return;
        }

        // Присвает уникальный uuid, если пользователь не указал свой uuid

        if (r.uuid == null) {
            r.uuid = UUID.randomUUID().toString();
            System.out.println("Вы не указали uuid, поэтому для данного резюме был присвоен уникальный uuid: " + r.uuid);
        }
        if (indexOfUUID(r.uuid) != -1) {
            System.out.println("Резюме с таким uuid уже существует!");
        } else storage[size++] = r;
    }

    Resume get(String uuid) {
        int index = indexOfUUID(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = indexOfUUID(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size -= 1;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
