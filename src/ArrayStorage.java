import java.util.Arrays;
import java.util.UUID;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for(int i = 0; i < size; i++) storage[i] = null;
        size = 0;
    }

    void save(Resume r) {
        boolean contain = false;
        if (r.uuid == null) {
            r.uuid = UUID.randomUUID().toString();
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(r.uuid)) {
                    contain = true;
                    break;
                }
            }
        }
        if (!contain) storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size -= 1;
                break;
            }
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
