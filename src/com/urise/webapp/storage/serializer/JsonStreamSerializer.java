package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;
import com.urise.webapp.util.JsonParser;
import com.urise.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(r, w);
        }
    }

    @Override
    public Resume doRead(InputStream io) throws IOException {
        try (Reader r = new BufferedReader(new InputStreamReader(io, StandardCharsets.UTF_8))) {
            return JsonParser.read(r, Resume.class);
        }
    }
}
