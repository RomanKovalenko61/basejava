package storage;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AllTestStorage.class);

        System.out.println("Total numbers of test " + result.getRunCount());
        System.out.println("Total numbers of failure test " + result.getFailureCount());

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
        }

        System.out.println("Total numbers of ignore test " + result.getIgnoreCount());
        System.out.println("All test was successful: " + result.wasSuccessful());

    }
}
