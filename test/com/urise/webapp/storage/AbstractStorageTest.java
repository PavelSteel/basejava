package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import org.junit.Before;
import org.junit.Test;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {

    protected Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";


    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;


    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail_1@ya.ru");
        R1.addContact(ContactType.PHONE, "1234567");
        R1.addSection(SectionType.OBJECTIVE, new TextSection("Objective_1"));
        R1.addSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2", "Achievement3"));
        R1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "Kotlin"));
        R1.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization1", "http://Organization1.ru",
                        new Organization.Position(2009, Month.APRIL, "position1", "content1"),
                        new Organization.Position(2007, Month.JUNE, 2008, Month.AUGUST, "position0", "content0"))));
        R1.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization("Institute", null,
                        new Organization.Position(2006, Month.JANUARY, 2008, Month.DECEMBER, "student", "Faculty of Law"),
                        new Organization.Position(2008, Month.SEPTEMBER, 2010, Month.AUGUST, "student", "ship mechanic faculty")),
                new Organization("Organization2", "http://Organization2.ru")));
        R2.addContact(ContactType.SKYPE, "Skype_1");
        R2.addContact(ContactType.PHONE, "7654321");
        R2.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization("Organization3", "http://Organization3.ru",
                        new Organization.Position(2011, Month.APRIL, "position1", "content1"))));
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "NewName");
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        assertSize(4);
        assertGet(R4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));

    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}