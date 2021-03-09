package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.UUID;

public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();
    public final static Resume RESUME_1;
    public final static Resume RESUME_2;
    public final static Resume RESUME_3;
    public final static Resume DUMMY;

    static {
        RESUME_1 = new Resume(UUID_1, "Roman");
        RESUME_2 = new Resume(UUID_2, "Name2");
        RESUME_3 = new Resume(UUID_3, "Name3");
        DUMMY = new Resume(UUID_4, "dummy");


        RESUME_1.addContacts(ContactType.CITY, "Rostov-on-Don");
        RESUME_1.addContacts(ContactType.PHONE, "8-800-535-35-35");
        RESUME_1.addContacts(ContactType.EMAIL, "astek14@mail.ru");
        RESUME_1.addContacts(ContactType.SKYPE, "evil_romashka");
        RESUME_1.addContacts(ContactType.PROFILE_HABR, "astek14");
        RESUME_1.addContacts(ContactType.PROFILE_STACK, "astek14");
        RESUME_1.addContacts(ContactType.ACCOUNT_GIT, "RomanKovalenko61");

        DUMMY.addContacts(ContactType.SKYPE, "Dummy Skype");
        DUMMY.addContacts(ContactType.PHONE, "8-800-800-80-80");

        RESUME_1.addSections(SectionType.OBJECTIVE, new TextSection("trainee"));
        RESUME_1.addSections(SectionType.PERSONAL, new TextSection("active, friendly"));

        ListSection achievement = new ListSection();
        achievement.addNoteToList("Начал изучать Javarush");
        achievement.addNoteToList("Поступил на крутую стажировку basejava");
        RESUME_1.addSections(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addNoteToList("Java core");
        qualifications.addNoteToList("Java Collections Framework");
        RESUME_1.addSections(SectionType.QUALIFICATIONS, qualifications);

        Organization education = new Organization("DSTU", "dstu.ru");
        education.addNoteToPosition(LocalDate.of(2007, 5, 1), LocalDate.of(2010, 5, 1), "student", "Mehatronics");
        OrganizationSection educationSection = new OrganizationSection(education);
        RESUME_1.addSections(SectionType.EDUCATION, educationSection);

        Organization experience = new Organization("Javaops", "javaops.ru");
        experience.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "trainee", null);

        Organization experience1 = new Organization("Javarush", "javarush.ru");
        experience1.addNoteToPosition(LocalDate.of(2018, 1, 21), LocalDate.of(2019, 8, 21), "listener", null);
        experience1.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "spectator", null);

        RESUME_1.addSections(SectionType.EXPERIENCE, new OrganizationSection(experience, experience1));

        RESUME_2.addContacts(ContactType.SKYPE, "skype2");
        RESUME_2.addContacts(ContactType.PHONE, "22222");
    }
}
