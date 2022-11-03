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
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            System.out.println("Resume " + resume + " updated.");
        } else {
            printNotResume(resume.getUuid());
        }

    }

    public void save(Resume r) {
        if (size == storage.length) {
            System.out.println("Array is full!");
        } else if (getIndex(r.getUuid()) != -1) {
            System.out.println("This resume is already there!");
        } else {
            storage[size] = r;
            size++;
        }

    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            printNotResume(uuid);
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            printNotResume(uuid);
        }


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

    private void printNotResume(String uuid) {
        System.out.println("No such resume " + uuid);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }


}
