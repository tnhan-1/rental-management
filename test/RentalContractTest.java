import rental.*;
import java.time.LocalDate;

// Plain Java test (no library needed). Run: see the command in the pull request description.
public class RentalContractTest {
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

        // valid creation
        Property p = new Property(101, "Apartment A101", 5000.50);
        RentalContract c = new RentalContract(1001, p, tenant, start, end);
        check("valid: contract is active", c.isActive());
        check("valid: rent amount is copied from the property", c.getRentAmount() == 5000.50);
        check("valid: property becomes unavailable", !p.isAvailable());
        p.updateRentalRate(5500);
        check("rent amount stays 5000.50 after the property rate changes", c.getRentAmount() == 5000.50);

        // invalid creation: every failure must leave the property available
        Property q = new Property(102, "Apartment A102", 4000);
        check("invalid: ContractID = 0", rejected(() -> new RentalContract(0, q, tenant, start, end)));
        check("invalid: ContractID = -4", rejected(() -> new RentalContract(-4, q, tenant, start, end)));
        check("invalid: null property", rejected(() -> new RentalContract(1, null, tenant, start, end)));
        check("invalid: null tenant", rejected(() -> new RentalContract(1, q, null, start, end)));
        check("invalid: null startDate", rejected(() -> new RentalContract(1, q, tenant, null, end)));
        check("invalid: null endDate", rejected(() -> new RentalContract(1, q, tenant, start, null)));
        check("invalid: endDate equals startDate", rejected(() -> new RentalContract(1, q, tenant, start, start)));
        check("invalid: endDate before startDate", rejected(() -> new RentalContract(1, q, tenant, end, start)));
        check("failed creations leave the property available", q.isAvailable());

        // already rented property
        check("invalid: property already rented", rejected(() -> new RentalContract(1002, p, tenant, start, end)));
        check("existing contract is unchanged after a failed creation", c.isActive() && !p.isAvailable());

        // terminate
        c.terminate();
        check("terminate: contract becomes inactive", !c.isActive());
        check("terminate: property becomes available", p.isAvailable());
        RentalContract fresh = new RentalContract(1003, p, tenant, start, end);
        c.terminate();
        check("old contract terminated again does not free the re-rented property", !p.isAvailable() && fresh.isActive());

        System.out.println("RentalContractTest: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}
