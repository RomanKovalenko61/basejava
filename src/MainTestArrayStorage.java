/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.uuid = "uuid1";
        Resume r2 = new Resume();
        r2.uuid = "uuid2";
        Resume r3 = new Resume();
        r3.uuid = "uuid3";

        // my update for test
        Resume r4 = new Resume();
        r4.uuid = "dummy";

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        // print all not null resume in storage
        printAll();

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("Get dummy: " + ARRAY_STORAGE.get(r4));

        //test update
        ARRAY_STORAGE.update(r1);
        System.out.println("Update r1: " + ARRAY_STORAGE.get(r1));
        ARRAY_STORAGE.update(r4);
        System.out.println("Update r4: " + ARRAY_STORAGE.get(r4));

        printAll();
        ARRAY_STORAGE.delete(r1);
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
        System.out.println("Resume number two in storage: " + ARRAY_STORAGE.storage[1]);
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
