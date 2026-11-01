import java.time.LocalDate;

// Plain Java test (no library needed). Run: see the command in the pull request description.
public class TenantTest {
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
        LocalDate start = LocalDate.of(2026, 11, 1);
        LocalDate end = LocalDate.of(2027, 10, 31);
        Tenant tenant = new Tenant(2, "Minh", "Tran", "minh@example.com");

        check("new tenant keeps the inherited full name", tenant.getFullName().equals("Minh Tran"));
        check("new tenant has no contracts", tenant.getRentalContracts().isEmpty());

        // valid rental
        Property p = new Property(101, "Apartment A101", 5000);
        RentalContract c = tenant.rentProperty(1001, p, start, end);
        check("valid rental returns an active contract", c.isActive() && c.getRentAmount() == 5000);
        check("valid rental appends exactly that contract", tenant.getRentalContracts().size() == 1 && tenant.getRentalContracts().get(0) == c);
        check("valid rental makes the property unavailable", !p.isAvailable());

        // invalid rentals: nothing is appended
        check("invalid: property already rented", rejected(() -> tenant.rentProperty(1002, p, start, end)));
        check("no contract appended for an unavailable property", tenant.getRentalContracts().size() == 1);

        Property q = new Property(102, "Apartment A102", 4000);
        check("invalid: endDate before startDate", rejected(() -> tenant.rentProperty(1003, q, end, start)));
        check("invalid: endDate equals startDate", rejected(() -> tenant.rentProperty(1003, q, start, start)));
        check("no contract appended for reversed dates", tenant.getRentalContracts().size() == 1);
        check("failed rentals leave the property available", q.isAvailable());

        // terminated contracts stay in the list
        c.terminate();
        check("terminated contract stays in RentalContracts", tenant.getRentalContracts().size() == 1 && tenant.getRentalContracts().contains(c));
        check("property is available again after terminate", p.isAvailable());
        RentalContract again = tenant.rentProperty(1004, p, start, end);
        check("tenant can rent the property again", tenant.getRentalContracts().size() == 2 && tenant.getRentalContracts().get(1) == again);

        System.out.println("TenantTest: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
