package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected final static int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printNotResume(uuid);
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected void printNotResume(String uuid) {
        System.out.println("No such resume " + uuid);
    }

}
