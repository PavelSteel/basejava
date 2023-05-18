package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();


    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;


    static {

        //STORAGE_DIR = new File("C:\\Users\\Вероника\\Desktop\\Topjava\\basejava\\storage");
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.addContact(ContactType.MAIL, "mail_1@ya.ru");
        R1.addContact(ContactType.PHONE, "1234567");
        R4.addContact(ContactType.PHONE, "4444");
        R4.addContact(ContactType.SKYPE, "Skype4");
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
}
