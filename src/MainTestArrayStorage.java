import model.Resume;
import storage.SortedArrayStorage;
import storage.Storage;

/**
 * Test for your storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");
        Resume r7 = new Resume("uuid7");
        Resume r8 = new Resume("uuid8");
        Resume r9 = new Resume("uuid9");
        Resume r10 = new Resume("uuid1");

        // my update for test
        Resume r4 = new Resume("dummy");

        ARRAY_STORAGE.save(r1);
        //printAll();
        ARRAY_STORAGE.save(r5);
        //printAll();
        ARRAY_STORAGE.save(r10);
        //printAll();
        ARRAY_STORAGE.save(r6);
        //printAll();
        ARRAY_STORAGE.save(r2);
        //printAll();
        ARRAY_STORAGE.save(r3);
        //printAll();
        ARRAY_STORAGE.save(r8);
        //printAll();
        ARRAY_STORAGE.save(r7);
        //printAll();


        // print all not null resume in storage
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get r9: " + ARRAY_STORAGE.get(r9.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("Get dummy: " + ARRAY_STORAGE.get(r4.getUuid()));

        //test update
        ARRAY_STORAGE.update(r1);
        System.out.println("Update r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        ARRAY_STORAGE.update(r4);
        System.out.println("Update r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        ARRAY_STORAGE.update(r3);
        System.out.println("Update r3: " + ARRAY_STORAGE.get(r3.getUuid()));

        printAll();
        System.out.println("Delete r1");
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        System.out.println("Delete r10");
        ARRAY_STORAGE.delete(r10.getUuid());
        printAll();
        System.out.println("Delete r3");
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        System.out.println("Delete r8");
        ARRAY_STORAGE.delete(r8.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());

    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
