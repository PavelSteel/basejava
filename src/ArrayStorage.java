import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size++;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (i != storage.length - 1) {
                if (storage[i].uuid.equals(uuid)) {
                    for (int j = i; j < storage.length - 1; j++) {
                        storage[j] = storage[j + 1];
                    }
                    size--;
                }
            } else {
                if (storage[i].uuid.equals(uuid)) {
                    storage[i] = null;
                    size--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        return size;
    }
}
