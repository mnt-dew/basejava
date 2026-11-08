package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    @Override
    public void update(Resume resume) {
        int idx = getIndexOfResume(resume.getUuid());
        updateResumeInStorage(resume, idx);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();

        int idx = indexOfUuid(uuid);

        if (idx >= 0) {
            throw new ExistStorageException(uuid);
        }

        saveResumeInStorage(resume, idx);
    }

    @Override
    public Resume get(String uuid) {
        int idx = getIndexOfResume(uuid);
        return getResumeByIndex(idx);
    }

    @Override
    public void delete(String uuid) {
        int idx = getIndexOfResume(uuid);
        deleteResumeFromStorage(idx);
    }

    private int getIndexOfResume(String uuid) {
        int idx = indexOfUuid(uuid);

        if (idx < 0) {
            throw new NotExistStorageException(uuid);
        }

        return idx;
    }

    protected abstract int indexOfUuid(String uuid);

    protected abstract void updateResumeInStorage(Resume resume, int idx);

    protected abstract void saveResumeInStorage(Resume resume, int idx);

    protected abstract Resume getResumeByIndex(int idx);

    protected abstract void deleteResumeFromStorage(int idx);
}
