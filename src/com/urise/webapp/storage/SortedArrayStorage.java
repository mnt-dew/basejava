package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    public void update(Resume resume) {
        int idx = indexOfUuid(resume.getUuid());

        if (idx < 0) {
            errorMessage("Резюме c uuid: " + resume.getUuid() + " не существует!");
            return;
        }

        storage[idx] = resume;
    }

    public void save(Resume resume) {
        int idx = indexOfUuid(resume.getUuid());
        int posIdx = -(idx);

        if (idx >= 0) {
            errorMessage("Резюме c uuid: " + resume.getUuid() + " уже существует!");
            return;
        }

        System.arraycopy(storage, posIdx - 1, storage, posIdx, size - (posIdx - 1));
        storage[posIdx - 1] = resume;
        size++;
    }

    public void delete(String uuid) {
        int idx = indexOfUuid(uuid);

        if (idx < 0) {
            errorMessage("Резюме c uuid: " + uuid + " не существует!");
            return;
        }

        System.arraycopy(storage, idx + 1, storage, idx, size - (idx - 1));
        storage[size--] = null;
    }

    protected int indexOfUuid(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
