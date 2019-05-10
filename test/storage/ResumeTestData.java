package storage;

import model.*;

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

        String objective = "trainee";
        resume.addSectionTypeMap(SectionType.OBJECTIVE, objective);

        String personal = "active, friendly";
        resume.addSectionTypeMap(SectionType.PERSONAL, personal);

        ListSectionType achievement = new ListSectionType();
        achievement.addNote("Начал изучать Javarush");
        achievement.addNote("Поступил на крутую стажировку basejava");
        resume.addSectionTypeMap(SectionType.ACHIEVEMENT, achievement);

        ListSectionType qualifications = new ListSectionType();
        qualifications.addNote("Java core");
        qualifications.addNote("Java Collections Framework");
        resume.addSectionTypeMap(SectionType.QUALIFICATIONS, qualifications);

        NoteAboutExperience education = new NoteAboutExperience("DSTU", "2007-2010", "Mehatronics");
        resume.addSectionTypeMap(SectionType.EDUCATION, education);

        NoteAboutExperience experience = new NoteAboutExperience("Javaops", "2019-2019", "trainee");
        resume.addSectionTypeMap(SectionType.EXPERIENCE, experience);

        printResume(resume);

        System.out.println("_____________________________________________");
        System.out.println("Print update resume ");
        System.out.println("_____________________________________________");

        phone = "8-950-859-82-15";
        resume.updateContactTypeMap(ContactType.PHONE, phone);


        qualifications.addNote("JUnit");
        qualifications.addNote("Russian : fluently, English : with dictionary");
        resume.updateSectionTypeMap(SectionType.PERSONAL, qualifications);

        printResume(resume);
    }
}
