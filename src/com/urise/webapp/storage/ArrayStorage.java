package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int idx = indexOfUUID(resume.getUuid());
        if (idx != -1) {
            storage[idx] = new Resume(resume.getUuid());
        } else {
            errorMessage("Такого резюме не существует!");
        }
    }

    public void save(Resume resume) {
        if (size == storage.length) {
            errorMessage("Хранилище резюме заполнено!");
        }
        if (indexOfUUID(resume.getUuid()) != -1) {
            errorMessage("Резюме c uuid: " + resume.getUuid() + " уже существует!");
        } else {
            storage[size++] = resume;
        }
    }

    public Resume get(String uuid) {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            return storage[idx];
        }
        errorMessage("Резюме c uuid: " + uuid + " не существует!");
        return null;
    }

    public void delete(String uuid) {
        int idx = indexOfUUID(uuid);
        if (idx != -1) {
            storage[idx] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            errorMessage("Резюме c uuid: " + uuid + " не существует!");
        }
    }

    private void errorMessage(String msg) {
        System.out.println(msg);
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
