package storage;

import model.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid0", "Roman");

        String city = "Rostov-on-Don";
        resume.addContactTypeMap(ContactType.CITY,
                new TextSectionType(ContactType.CITY.getTitle(), city));

        String phone = "8-800-535-35-35";
        resume.addContactTypeMap(ContactType.PHONE,
                new TextSectionType(ContactType.PHONE.getTitle(), phone));

        String email = "astek14@mail.ru";
        resume.addContactTypeMap(ContactType.EMAIL,
                new TextSectionType(ContactType.EMAIL.getTitle(), email));

        String skype = "evil_romashka";
        resume.addContactTypeMap(ContactType.SKYPE,
                new TextSectionType(ContactType.SKYPE.getTitle(), skype));

        String habr = "astek14";
        resume.addContactTypeMap(ContactType.PROFILE_HABR,
                new TextSectionType(ContactType.PROFILE_HABR.getTitle(), habr));

        String stack = "astek14";
        resume.addContactTypeMap(ContactType.PROFILE_STACK,
                new TextSectionType(ContactType.PROFILE_STACK.getTitle(), stack));

        String git = "RomanKovalenko61";
        resume.addContactTypeMap(ContactType.ACCOUNT_GIT,
                new TextSectionType(ContactType.ACCOUNT_GIT.getTitle(), git));

        String objective = "trainee";
        resume.addSectionTypeMap(SectionType.OBJECTIVE,
                new TextSectionType(SectionType.OBJECTIVE.getTitle(), objective));

        String personal = "active, friendly";
        resume.addSectionTypeMap(SectionType.PERSONAL,
                new TextSectionType(SectionType.PERSONAL.getTitle(), personal));

        ListSectionType achievement = new ListSectionType(SectionType.ACHIEVEMENT);
        achievement.addString("Начал изучать Javarush");
        achievement.addString("Поступил на крутую стажировку basejava");
        resume.addSectionTypeMap(achievement.getTitle(), achievement);

        ListSectionType qualifications = new ListSectionType(SectionType.QUALIFICATIONS);
        qualifications.addString("Java core");
        qualifications.addString("Java Collections Framework");
        resume.addSectionTypeMap(achievement.getTitle(), qualifications);

        TableSectionType education = new TableSectionType(SectionType.EDUCATION);
        education.addString("DSTU", "2007-2010", "Mehatronics");
        resume.addSectionTypeMap(education.getTitle(), education);

        TableSectionType experience = new TableSectionType(SectionType.EXPERIENCE);
        experience.addString("Javaops", "2019-2019", "trainee");
        resume.addSectionTypeMap(experience.getTitle(), experience);

        resume.printThisResume();
        System.out.println(" ");
        System.out.println("______________________________________");
        System.out.println("  ");

        phone = "8-950-859-82-15";
        resume.updateContactTypeMap(ContactType.PHONE,
                new TextSectionType(ContactType.PHONE.getTitle(), phone));


        qualifications.addString("JUnit");
        qualifications.addString("Russian : fluently, English : with dictionary");
        resume.updateSectionTypeMap(achievement.getTitle(), qualifications);

        resume.printThisResume();
    }
}
