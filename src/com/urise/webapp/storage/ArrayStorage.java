package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

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

    public Resume get(String uuid) {
        int idx = indexOfUuid(uuid);
        if (idx >= 0) {
            return storage[idx];
        }
        errorMessage("Резюме c uuid: " + uuid + " не существует!");
        return null;
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

    private void errorMessage(String msg) {
        System.out.println(msg);
    }

    private int indexOfUuid(String uuid) {
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
