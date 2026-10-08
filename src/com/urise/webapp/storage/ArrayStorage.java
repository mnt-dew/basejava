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
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) throws Exception {
        int idx = indexOfUUID(resume.getUuid());
        if (idx != -1) {
            storage[idx] = new Resume(resume.getUuid());
        } else {
            throw new Exception("Такого резюме не существует!");
        }
    }

    public void save(Resume resume) throws Exception {
        if (size == storage.length) {
            throw new Exception("Хранилище резюме заполнено!");
        }
        if (indexOfUUID(resume.getUuid()) != -1) {
            throw new Exception("Резюме c uuid: " + resume.getUuid() + " уже существует!");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) throws Exception {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            return storage[idx];
        }
        throw new Exception("Резюме c uuid: " + uuid + " уже существует!");
    }

    public void delete(String uuid) throws Exception {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            throw new Exception("Резюме c uuid: " + uuid + " уже существует!");
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
