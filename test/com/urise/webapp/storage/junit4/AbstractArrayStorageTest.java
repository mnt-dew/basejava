package com.urise.webapp.storage.junit4;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.*;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume resume1 = new Resume(UUID_1);
    private static final Resume resume2 = new Resume(UUID_2);
    private static final Resume resume3 = new Resume(UUID_3);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void clearTest() {
        storage.clear();
        assertEquals(0, storage.size());
        assertEquals(0, storage.getAll().length);
    }

    @Test
    public void updateTest() {
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        assertSame(updatedResume, storage.get(UUID_1));
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResumeTest() {
        Resume updatedResume = new Resume(UUID_4);
        storage.update(updatedResume);
    }

    @Test
    public void saveTest() {
        Resume newResumeForSave = new Resume(UUID_4);
        storage.save(newResumeForSave);
        assertEquals(4, storage.size());
        assertEquals(newResumeForSave, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResumeTest() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void storageOverflowTest() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(Integer.toString(i)));
            }
        } catch (StorageException e) {
            fail("Переполнение произошло раньше времени");
        }

        storage.save(new Resume("overflow_resume"));
    }

    @Test
    public void getTest() {
        assertEquals(resume1, storage.get(UUID_1));
        assertEquals(resume2, storage.get(UUID_2));
        assertEquals(resume3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResumeTest() {
        storage.get(UUID_4);
    }

    @Test
    public void deleteTest() {
        storage.delete(UUID_3);
        assertEquals(2, storage.size());
        assertEquals(resume1, storage.get(UUID_1));
        assertEquals(resume2, storage.get(UUID_2));
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResumeTest() {
        storage.delete(UUID_4);
    }

    @Test
    public void sizeTest() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAllTest() {
        Resume[] expected = {resume1, resume2, resume3};
        Resume[] actual = storage.getAll();
        assertArrayEquals(expected, actual);
    }
}