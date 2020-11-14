package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("src\\ru\\javawebinar\\basejava\\folder");
    Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private final static Resume RESUME_1;
    private final static Resume RESUME_2;
    private final static Resume RESUME_3;
    protected final static Resume DUMMY;

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

        Organization education = new Organization("DSTU", "http://dstu.ru/");
        education.addNoteToPosition(LocalDate.of(2007, 5, 1), LocalDate.of(2010, 5, 1), "student", "Mehatronics");
        OrganizationSection educationSection = new OrganizationSection(education);
        RESUME_1.addSections(SectionType.EDUCATION, educationSection);

        Organization experience = new Organization("Javaops", "");
        experience.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "trainee", null);

        Organization experience1 = new Organization("Javarush", "");
        experience1.addNoteToPosition(LocalDate.of(2018, 1, 21), LocalDate.of(2019, 8, 21), "listener", null);
        experience1.addNoteToPosition(LocalDate.of(2019, 1, 21), LocalDate.of(2019, 8, 21), "spectator", null);

        RESUME_1.addSections(SectionType.EXPERIENCE, new OrganizationSection(experience, experience1));

        RESUME_2.addContacts(ContactType.SKYPE, "skype2");
        RESUME_2.addContacts(ContactType.PHONE, "22222");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void save() {
        storage.save(DUMMY);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(DUMMY, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test
    public void get() {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_4);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_2, "Boris Blade");
        storage.update(newResume);
        Assert.assertEquals(newResume, RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(DUMMY);
    }

    @Test
    public void getAllSorted() {
        List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(resumes);
        List<Resume> allResume = storage.getAllSorted();
        Assert.assertEquals(resumes, allResume);
    }
}