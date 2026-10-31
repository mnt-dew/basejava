package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    public static final int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int idx = indexOfUuid(resume.getUuid());

        if (idx < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }

        storage[idx] = resume;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();

        if (size == STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено!", uuid);
        }

        int idx = indexOfUuid(uuid);

        if (idx >= 0) {
            throw new ExistStorageException(uuid);
        }

        insertResumeInStorage(resume, idx);
        size++;
    }

    public Resume get(String uuid) {
        int idx = indexOfUuid(uuid);

        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }

        return storage[idx];
    }

    public void delete(String uuid) {
        int idx = indexOfUuid(uuid);

        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }

        deleteElementFromStorage(idx);
        storage[size - 1] = null;
        size--;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int indexOfUuid(String uuid);

    protected abstract void insertResumeInStorage(Resume resume, int idx);

    protected abstract void deleteElementFromStorage(int idx);


}
