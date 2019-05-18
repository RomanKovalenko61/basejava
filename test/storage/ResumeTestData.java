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

        Position education = new Position("DSTU", "<a>link<a>", "Mehatronics");
        education.setStartDate(2007, 6, 1);
        education.setEndDatend(2010, 6, 1);
        PositionSection educations = new PositionSection();
        educations.addNoteToListPosition(education);
        resume.addSectionTypeMap(SectionType.EDUCATION, educations);

        Position experience = new Position("Javaops", "<a>link<a>", "trainee");
        experience.setStartDate(2019, 2, 21);
        experience.setEndDatend(2019, 8, 21);
        PositionSection experiences = new PositionSection();
        experiences.addNoteToListPosition(experience);
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
    }
}
