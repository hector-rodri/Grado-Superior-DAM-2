package bug6;

/**
 * What is this design pattern?
 * What it could be wrong in this case? How many instances are created in this context?
 * Because there is no synchronization, multiple threads could create multiple instances simultaneously.
 *
 * Modify a single line of code such that the Singleton to work as expected.
 */

public class Singleton {
    private static volatile Singleton instance;//The change is here, we put volatile to ensure visibility of changes across threads
    private static int numberOfInstances = 0;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null) {
            System.out.println("Creating only one instance");
            instance = new Singleton();
            ++numberOfInstances;
        }
        return instance;
    }

    public static int getNumberOfInstances() {
        return numberOfInstances;
    }
}
