package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 100000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

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
}
