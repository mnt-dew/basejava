package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void update(Resume resume) {
        int idx = indexOfUuid(resume.getUuid());
        if (idx >= 0) {
            storage[idx] = resume;
        } else {
            errorMessage("Резюме с uuid: " + resume.getUuid() + " в хранилище не существует!");
        }
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            errorMessage("Хранилище резюме заполнено!");
            return;
        }
        if (indexOfUuid(resume.getUuid()) != -1) {
            errorMessage("Резюме c uuid: " + resume.getUuid() + " уже существует!");
            return;
        }
        storage[size++] = resume;
    }

    public void delete(String uuid) {
        int idx = indexOfUuid(uuid);
        if (idx >= 0) {
            size--;
            storage[idx] = storage[size];
            storage[size] = null;
        } else {
            errorMessage("Резюме c uuid: " + uuid + " не существует!");
        }
    }

    protected int indexOfUuid(String uuid) {
        for (int idx = 0; idx < size; idx++) {
            if (storage[idx].getUuid().equals(uuid)) {
                return idx;
            }
        }
        return -1;
    }
}
