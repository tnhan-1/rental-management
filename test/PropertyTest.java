import rental.*;

public class PropertyTest {
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
        // Valid creation
        Property property = new Property(101, "Apartment A101", 5000.50);
        check("valid: PropertyID matches", property.getPropertyId() == 101);
        check("valid: Description matches", "Apartment A101".equals(property.getDescription()));
        check("valid: RentalRate matches", property.getRentalRate() == 5000.50);
        check("valid: initially available", property.isAvailable());

        // Invalid PropertyID
        check("invalid: ID = 0", rejected(() -> new Property(0, "Apartment A101", 5000.50)));
        check("invalid: ID = -1", rejected(() -> new Property(-1, "Apartment A101", 5000.50)));

        // Invalid Description
        check("invalid: null description", rejected(() -> new Property(102, null, 5000.50)));
        check("invalid: empty description", rejected(() -> new Property(102, "", 5000.50)));
        check("invalid: whitespace-only description", rejected(() -> new Property(102, "   ", 5000.50)));

        // Invalid RentalRate
        check("invalid: rate = 0", rejected(() -> new Property(103, "Apartment A103", 0)));
        check("invalid: rate = -50", rejected(() -> new Property(103, "Apartment A103", -50)));
        check("invalid: rate = NaN", rejected(() -> new Property(103, "Apartment A103", Double.NaN)));
        check("invalid: rate = Positive Infinity", rejected(() -> new Property(103, "Apartment A103", Double.POSITIVE_INFINITY)));

        // Update Description
        property.updateDescription("Apartment A101 Renovated");
        check("update description: valid", "Apartment A101 Renovated".equals(property.getDescription()));
        check("update description: invalid empty", rejected(() -> property.updateDescription("   ")));
        check("update description: invalid null", rejected(() -> property.updateDescription(null)));
        check("update description failure leaves old value", "Apartment A101 Renovated".equals(property.getDescription()));

        // Update RentalRate
        property.updateRentalRate(5500.0);
        check("update rental rate: valid", property.getRentalRate() == 5500.0);
        check("update rental rate: invalid rate = -100", rejected(() -> property.updateRentalRate(-100)));
        check("update rental rate: invalid rate = 0", rejected(() -> property.updateRentalRate(0)));
        check("update rental rate failure leaves old rate", property.getRentalRate() == 5500.0);

        System.out.println("PropertyTest: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}