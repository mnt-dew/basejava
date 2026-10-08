package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int idx = 0; idx < size; idx++) {
            storage[idx] = null;
        }
        size = 0;
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            System.out.println("Хранилище резюме заполнено!");
            return;
        }
        if (indexOfUUID(resume.getUuid()) != -1) {
            System.out.println("Резюме с таким uuid уже существует!");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int idx = indexOfUUID(uuid);
        return idx != -1 ? storage[idx] : null;
    }

    public void delete(String uuid) {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    private int indexOfUUID(String uuid) {
        for (int idx = 0; idx < size; idx++) {
            if (storage[idx].getUuid().equals(uuid)) {
                return idx;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
