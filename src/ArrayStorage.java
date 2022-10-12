/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length-1; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume g = new Resume();
        for (int i = 0; i < storage.length-1; i++) {
            if (storage[i].toString().equals(uuid)) {
                g = storage[i];
            } else {
                g = null;
            }
        }
        return g;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (i != storage.length - 1) {
                if (storage[i].toString().equals(uuid)) {
                    for (int j = i; j < storage.length; j++) {
                        storage[j] = storage[j + 1];
                    }
                }
            } else {
                storage[i] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
