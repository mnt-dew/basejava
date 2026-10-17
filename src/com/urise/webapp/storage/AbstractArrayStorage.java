package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int idx = indexOfUuid(resume.getUuid());

        if (idx < 0) {
            errorResumeNotFound(resume.getUuid());
            return;
        }

        storage[idx] = resume;
    }

    public void save(Resume resume) {
        if (size == STORAGE_LIMIT) {
            System.out.println("Хранилище резюме заполнено!");
            return;
        }

        int idx = indexOfUuid(resume.getUuid());

        if (idx >= 0) {
            System.out.println("Резюме с uuid: " + resume.getUuid() + " уже существует!");
            return;
        }

        insertElementInStorage(resume, idx);
        size++;
    }

    public void delete(String uuid) {
        int idx = indexOfUuid(uuid);

        if (idx < 0) {
            errorResumeNotFound(uuid);
            return;
        }

        deleteElementFromStorage(idx);
        size--;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int idx = indexOfUuid(uuid);
        if (idx >= 0) {
            return storage[idx];
        }
        errorResumeNotFound(uuid);
        return null;
    }

    private void errorResumeNotFound(String uuid) {
        System.out.println("Резюме c uuid: " + uuid + " не существует!");
    }

    protected abstract int indexOfUuid(String uuid);

    protected abstract void insertElementInStorage(Resume resume, int idx);

    protected abstract void deleteElementFromStorage(int idx);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
