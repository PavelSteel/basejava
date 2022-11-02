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
        for (int i = 0; i < size; i++) {
            if (coincide(i, resume.getUuid())) {
                storage[i] = resume;
            } else {
                printNotResume(resume.getUuid());
            }
        }
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (!contains(r.getUuid())) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("This resume is already there!");
            }
        } else {
            System.out.println("Array is full!");
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume();
        for (int i = 0; i < size; i++) {
            if (coincide(i, uuid)) {
                resume = storage[i];
            } else {
                resume = null;
                printNotResume(uuid);
                break;
            }
        }
        return resume;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (coincide(i, uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            } else {
                printNotResume(uuid);
            }
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

    private boolean coincide(int i, String uuid) {
        return storage[i].getUuid().equals(uuid);
    }

    private void printNotResume(String uuid) {
        System.out.println("No such resume " + uuid);
    }

    private boolean contains(String uuid) {
        boolean b = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                b = true;
                break;
            }
        }
        return b;
    }


}
