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

    public abstract void update(Resume resume);

    public abstract void save(Resume resume);

    public abstract void delete(String uuid);

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int idx = indexOfUuid(uuid);
        if (idx >= 0) {
            return storage[idx];
        }
        errorMessage("Резюме c uuid: " + uuid + " не существует!");
        return null;
    }

    protected void errorMessage(String msg) {
        System.out.println(msg);
    }

    protected abstract int indexOfUuid(String uuid);

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }
}
