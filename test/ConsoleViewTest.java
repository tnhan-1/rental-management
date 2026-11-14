import controller.RentalController;
import rental.Property;
import rental.RentalContract;
import view.ConsoleView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ConsoleViewTest {
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String testName, boolean condition) {
        if (condition) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }

    public static void main(String[] args) {
        RentalController rentalController = new RentalController();

        String simulatedUserInput = String.join("\n",
                "1", "101", "Apartment A101, two bedrooms", "5000.0",
                "1", "102", "Apartment A102", "-50.0",
                "2", "1001", "101", "2026-11-01", "2027-10-31",
                "2", "1002", "101", "2026-11-01", "2027-10-31",
                "3", "1001",
                "4"
        ) + "\n";

        InputStream originalSystemIn = System.in;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedUserInput.getBytes(StandardCharsets.UTF_8));
            Scanner scanner = new Scanner(inputStream);

            ConsoleView consoleView = new ConsoleView(rentalController, scanner);
            consoleView.start();

            Property createdProperty = consoleView.getProperties().get(101);
            check("Valid property created in memory", createdProperty != null);
            check("Valid property ID matches", createdProperty != null && createdProperty.getPropertyId() == 101);

            Property invalidProperty = consoleView.getProperties().get(102);
            check("Invalid property not stored in memory", invalidProperty == null);

            RentalContract createdContract = consoleView.getRentalContracts().get(1001);
            check("Rental contract created in memory", createdContract != null);

            check("Property becomes available after contract termination", createdProperty != null && createdProperty.isAvailable());
        } finally {
            System.setIn(originalSystemIn);
        }

        System.out.println("ConsoleViewTest: " + passed + " passed, " + failed + " failed");
        if (failed > 0) {
            System.exit(1);
        }
    }
}