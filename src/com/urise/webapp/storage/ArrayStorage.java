package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int indexOfUuid(String uuid) {
        for (int idx = 0; idx < size; idx++) {
            if (storage[idx].getUuid().equals(uuid)) {
                return idx;
            }
        }
        return -1;
    }

    @Override
    protected void insertResumeInStorage(Resume resume, int idx) {
        storage[size] = resume;
    }

    @Override
    protected void deleteElementFromStorage(int idx) {
        storage[idx] = storage[size - 1];
        storage[size - 1] = null;
    }
}
