package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.serialization.DataStreamSerializer;

import java.time.LocalDate;

public class ResumeTestData {

    private static void printResume(Resume resume) {
        System.out.println("Resume uuid : " + resume.getUuid());
        System.out.println("Resume fullName : " + resume.getFullName());

        ContactType[] contactTypes = ContactType.values();
        for (ContactType type : contactTypes) {
            System.out.println(type.getTitle() + ": " + resume.getContacts(type));
        }

        SectionType[] sectionTypes = SectionType.values();
        for (SectionType type : sectionTypes) {
            System.out.println(type.getTitle() + "\n" + resume.getSectionValue(type));
        }
    }

    public static void main(String[] args) {
        Resume resume = new Resume("uuid0", "Roman");
//        Resume resume1 = new Resume("uuid1", "Alex");
//        Resume resume2 = new Resume("uuid2", "Victor");
//        Resume resume3 = new Resume("uuid3", "Tanya");

        String city = "Rostov-on-Don";
        resume.addContacts(ContactType.CITY, city);

        String phone = "8-800-535-35-35";
        resume.addContacts(ContactType.PHONE, phone);

        String email = "astek14@mail.ru";
        resume.addContacts(ContactType.EMAIL, email);

        String skype = "evil_romashka";
        resume.addContacts(ContactType.SKYPE, skype);

        String habr = "astek14";
        resume.addContacts(ContactType.PROFILE_HABR, habr);

        String stack = "astek14";
        resume.addContacts(ContactType.PROFILE_STACK, stack);

        String git = "RomanKovalenko61";
        resume.addContacts(ContactType.ACCOUNT_GIT, git);

        TextSection personal = new TextSection("active, friendly");
        resume.addSections(SectionType.PERSONAL, personal);

        TextSection objective = new TextSection("trainee");
        resume.addSections(SectionType.OBJECTIVE, objective);

        ListSection achievement = new ListSection();
        achievement.addNoteToList("Начал изучать Javarush");
        achievement.addNoteToList("Поступил на крутую стажировку basejava");
        resume.addSections(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addNoteToList("Java core");
        qualifications.addNoteToList("Java Collections Framework");
        resume.addSections(SectionType.QUALIFICATIONS, qualifications);

        Organization education = new Organization("DSTU", "");
        education.addNoteToPosition(LocalDate.of(2007, 5, 1), LocalDate.of(2010, 5, 1), "student", "Mehatronics");
        OrganizationSection educationSection = new OrganizationSection(education);
        resume.addSections(SectionType.EDUCATION, educationSection);


        Organization experience = new Organization("Javaops", "");
        experience.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "trainee", null);


        Organization experience1 = new Organization("Javarush", "");
        experience1.addNoteToPosition(LocalDate.of(2018, 1, 21), LocalDate.of(2019, 8, 21), "listener", null);
        experience1.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "spectator", null);

        resume.addSections(SectionType.EXPERIENCE, new OrganizationSection(experience, experience1));

        printResume(resume);

        System.out.println("_____________________________________________");
        System.out.println("Print update resume ");
        System.out.println("update phone and qualification ");
        System.out.println("_____________________________________________");

        phone = "8-950-859-82-15";
        resume.updateContacts(ContactType.PHONE, phone);

        qualifications.addNoteToList("JUnit");
        qualifications.addNoteToList("Russian : fluently, English : with dictionary");
        resume.updateSections(SectionType.QUALIFICATIONS, qualifications);

        printResume(resume);

        Storage storage = new PathStorage("src\\ru\\javawebinar\\basejava\\folder", new DataStreamSerializer());
        storage.save(resume);

        Resume resumeRead = storage.get("uuid0");
        printResume(resumeRead);
    }
}
