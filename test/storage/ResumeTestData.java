package storage;

import model.*;

import java.io.File;
import java.time.LocalDate;

public class ResumeTestData {

    private static void printResume(Resume resume) {
        System.out.println("Resume uuid : " + resume.getUuid());
        System.out.println("Resume fullName : " + resume.getFullName());

        ContactType[] contactTypes = ContactType.values();
        for (ContactType type : contactTypes) {
            System.out.println(type.getTitle() + ": " + resume.getContactTypeMapValue(type));
        }

        SectionType[] sectionTypes = SectionType.values();
        for (SectionType type : sectionTypes) {
            System.out.println(type.getTitle() + "\n" + resume.getSectionTypeMapValue(type));
        }
    }

    public static void main(String[] args) {
        Resume resume = new Resume("uuid0", "Roman");
        Resume resume1 = new Resume("uuid1", "Alex");
        Resume resume2 = new Resume("uuid2", "Victor");
        Resume resume3 = new Resume("uuid3", "Tanya");

        String city = "Rostov-on-Don";
        resume.addContactTypeMap(ContactType.CITY, city);

        String phone = "8-800-535-35-35";
        resume.addContactTypeMap(ContactType.PHONE, phone);

        String email = "astek14@mail.ru";
        resume.addContactTypeMap(ContactType.EMAIL, email);

        String skype = "evil_romashka";
        resume.addContactTypeMap(ContactType.SKYPE, skype);

        String habr = "astek14";
        resume.addContactTypeMap(ContactType.PROFILE_HABR, habr);

        String stack = "astek14";
        resume.addContactTypeMap(ContactType.PROFILE_STACK, stack);

        String git = "RomanKovalenko61";
        resume.addContactTypeMap(ContactType.ACCOUNT_GIT, git);

        TextSection personal = new TextSection("active, friendly");
        resume.addSectionTypeMap(SectionType.PERSONAL, personal);

        TextSection objective = new TextSection("trainee");
        resume.addSectionTypeMap(SectionType.OBJECTIVE, objective);

        BulletListSection achievement = new BulletListSection();
        achievement.addNoteToList("Начал изучать Javarush");
        achievement.addNoteToList("Поступил на крутую стажировку basejava");
        resume.addSectionTypeMap(SectionType.ACHIEVEMENT, achievement);

        BulletListSection qualifications = new BulletListSection();
        qualifications.addNoteToList("Java core");
        qualifications.addNoteToList("Java Collections Framework");
        resume.addSectionTypeMap(SectionType.QUALIFICATIONS, qualifications);

        Position education = new Position("DSTU", "<a>link<a>");
        education.addNoteToPosition(LocalDate.of(2007, 5, 1), LocalDate.of(2010, 5, 1), "Mehatronics");
        PositionSection educations = new PositionSection();
        educations.addNoteToListPosition(education);
        resume.addSectionTypeMap(SectionType.EDUCATION, educations);

        Position experience = new Position("Javaops", "<a>link<a>");
        experience.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "trainee");
        PositionSection experiences = new PositionSection();
        experiences.addNoteToListPosition(experience);
        resume.addSectionTypeMap(SectionType.EXPERIENCE, experiences);

        Position experience1 = new Position("Javarush", "<a>link<a>");
        experience1.addNoteToPosition(LocalDate.of(2018, 1, 21), LocalDate.of(2019, 8, 21), "trainee");
        experience1.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "trainee");
        experiences.addNoteToListPosition(experience1);
        resume.addSectionTypeMap(SectionType.EXPERIENCE, experiences);

        printResume(resume);

        System.out.println("_____________________________________________");
        System.out.println("Print update resume ");
        System.out.println("_____________________________________________");

        phone = "8-950-859-82-15";
        resume.updateContactTypeMap(ContactType.PHONE, phone);


        qualifications.addNoteToList("JUnit");
        qualifications.addNoteToList("Russian : fluently, English : with dictionary");
        resume.updateSectionTypeMap(SectionType.PERSONAL, qualifications);

        printResume(resume);

        File dir = new File("D:\\DOCUM\\basejava\\directory");
        dir.mkdir();

        FileStorage directory = new FileStorage(dir);

        directory.saveToStorage(dir, resume);
        directory.saveToStorage(dir, resume1);
        directory.saveToStorage(dir, resume2);
        directory.saveToStorage(dir, resume3);

        System.out.println(directory.size());
        System.out.println("Clear directory");
        directory.clear();
        System.out.println(directory.size());
    }
}
