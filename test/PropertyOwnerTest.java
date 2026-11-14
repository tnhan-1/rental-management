// Plain Java test (no library needed). Run: see the command in the pull request description.
public class PropertyOwnerTest {
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean ok) {
        if (ok) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: " + name);
        }
    }

    private static boolean rejected(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        PropertyOwner owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");

        check("new owner keeps the inherited full name", owner.getFullName().equals("Anna Nguyen"));
        check("new owner has no properties", owner.getProperties().isEmpty());

        // Valid property creation
        Property prop = owner.createProperty(101, "Apartment A101", 5000.50);
        check("should append property on valid input", prop != null);
        check("owner contains created property", owner.getProperties().size() == 1 && owner.getProperties().get(0) == prop);

        // Invalid property creation: should throw IllegalArgumentException and NOT append
        check("should NOT append property when creation fails (negative rate)", rejected(() -> owner.createProperty(102, "Apartment A102", -50)));
        check("owner properties size remains 1 after failed creation", owner.getProperties().size() == 1);

        check("should NOT append property when ID <= 0", rejected(() -> owner.createProperty(0, "Apartment A103", 5000.50)));
        check("owner properties size remains 1 after invalid ID", owner.getProperties().size() == 1);

        check("should NOT append property when empty description", rejected(() -> owner.createProperty(104, "", 5000.50)));
        check("owner properties size remains 1 after invalid description", owner.getProperties().size() == 1);

        System.out.println("PropertyOwnerTest: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
