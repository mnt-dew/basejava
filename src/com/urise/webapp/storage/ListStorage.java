package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int indexOfUuid(String uuid) {
        ListIterator<Resume> storageIterator = storage.listIterator();
        while (storageIterator.hasNext()) {
            Resume re = storageIterator.next();
            int index = storageIterator.previousIndex();
            if (re.getUuid().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    protected void updateResumeInStorage(Resume resume, int idx) {
        storage.set(idx, resume);
    }

    @Override
    protected void saveResumeInStorage(Resume resume, int idx) {
        storage.add(resume);
    }

    @Override
    protected Resume getResumeByIndex(int idx) {
        return storage.get(idx);
    }

    @Override
    protected void deleteResumeFromStorage(int idx) {
        storage.remove(idx);
    }
}
