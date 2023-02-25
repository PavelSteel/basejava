package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new TreeMap<>();

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((String) searchKey);
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected String getSearchKey(String uuid) {
        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (entry.getKey().equals(uuid)) {
                return uuid;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] array = new Resume[map.size()];
        int x = 0;

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            if (x < map.size()) {
                array[x] = entry.getValue();
                x++;
            }
        }
        return array;
    }

    @Override
    public int size() {
        return map.size();
    }
}
