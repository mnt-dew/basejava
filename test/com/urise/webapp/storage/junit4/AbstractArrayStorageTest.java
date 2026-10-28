package com.urise.webapp.storage.junit4;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final Resume resume1 = new Resume("uuid1");
    private static final Resume resume2 = new Resume("uuid2");
    private static final Resume resume3 = new Resume("uuid3");

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
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
        Assert.assertEquals(0, storage.getAll().length);
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume("uuid1");
        storage.update(updatedResume);
        Assert.assertSame(updatedResume, storage.get("uuid1"));
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistResume() {
        Resume updatedResume = new Resume("uuid5");
        storage.update(updatedResume);
    }

    @Test
    public void save() {
        Resume newResumeForSave = new Resume("uuid4");
        storage.save(newResumeForSave);
        Assert.assertEquals(4, storage.size());
        Assert.assertSame(newResumeForSave, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistResume() {
        storage.save(resume1);
    }

    @Test(expected = StorageException.class)
    public void storageOverflow() {
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
    public void get() {
        Assert.assertEquals(resume1, storage.get("uuid1"));
        Assert.assertEquals(resume2, storage.get("uuid2"));
        Assert.assertEquals(resume3, storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistResume() {
        storage.get("dummy");
    }

    @Test
    public void delete() {
        storage.delete("uuid3");
        Assert.assertEquals(2, storage.size());
        Assert.assertEquals(resume1, storage.get("uuid1"));
        Assert.assertEquals(resume2, storage.get("uuid2"));
        Assert.assertThrows(NotExistStorageException.class, () -> storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete("uuid5");
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] expected = {resume1, resume2, resume3};
        Resume[] actual = storage.getAll();
        Assert.assertArrayEquals(expected, actual);
    }
}