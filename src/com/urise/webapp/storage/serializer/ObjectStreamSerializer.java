package com.urise.webapp.storage.serializer;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;

public class ObjectStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (ObjectOutputStream ous = new ObjectOutputStream(os)) {
            ous.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream io) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(io)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Resume read error", e);
        }
    }
}
