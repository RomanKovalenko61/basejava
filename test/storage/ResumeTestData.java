package storage;

import model.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid0", "Roman");

        String city = "Rostov-on-Don";

        resume.addContactTypeMap(ContactType.CITY,
                new TextSectionType(ContactType.CITY.getTitle(), city));

        String email = "astek14@mail.ru";

        resume.addContactTypeMap(ContactType.EMAIL,
                new TextSectionType(ContactType.EMAIL.getTitle(), email));

        ListSectionType achievement = new ListSectionType(SectionType.ACHIEVEMENT);
        achievement.addString("Начал изучать Javarush");
        achievement.addString("Поступил на крутую стажировку basejava");
        resume.addSectionTypeMap(achievement.getTitle(), achievement);

        TableSectionType education = new TableSectionType(SectionType.EDUCATION);
        education.addString("DSTU", "2007-2010", "Mehatronics");
        resume.addSectionTypeMap(education.getTitle(), education);

        resume.printThisResume();
    }
}
