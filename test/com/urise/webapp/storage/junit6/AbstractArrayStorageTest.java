package com.urise.webapp.storage.junit6;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.jupiter.api.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void storageOverflowTest() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                Resume newResume = new Resume(Integer.toString(i));
                storage.save(newResume);
            }
        } catch (StorageException e) {
            fail("Переполнение произошло раньше времени");
        }

        assertThrows(StorageException.class, () -> storage.save(new Resume("overflow_resume")));
    }
}