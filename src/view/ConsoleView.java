package view;

import controller.RentalController;
import rental.Property;
import rental.PropertyOwner;
import rental.RentalContract;
import rental.Tenant;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleView {
    private final RentalController rentalController;
    private final Scanner scanner;
    private final Map<Integer, Property> properties;
    private final Map<Integer, RentalContract> rentalContracts;
    private final PropertyOwner defaultPropertyOwner;
    private final Tenant defaultTenant;

    public ConsoleView(RentalController rentalController) {
        this(rentalController, new Scanner(System.in));
    }

    public ConsoleView(RentalController rentalController, Scanner scanner) {
        this.rentalController = rentalController;
        this.scanner = scanner;
        this.properties = new HashMap<>();
        this.rentalContracts = new HashMap<>();
        this.defaultPropertyOwner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
        this.defaultTenant = new Tenant(2, "Minh", "Tran", "minh@example.com");
    }

    public void start() {
        boolean isRunning = true;
        while (isRunning) {
            displayMenu();
            String userChoice = scanner.nextLine().trim();
            switch (userChoice) {
                case "1":
                    handleCreateProperty();
                    break;
                case "2":
                    handleRentProperty();
                    break;
                case "3":
                    handleTerminateContract();
                    break;
                case "4":
                    System.out.println("Exiting application.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
                    break;
            }
            System.out.println();
        }
    }

    public void displayMenu() {
        System.out.println("1. Create property 2. Rent 3. Terminate 4. Exit");
        System.out.print("Enter your choice: ");
    }

    public void handleCreateProperty() {
        try {
            System.out.print("Enter property identifier: ");
            int propertyId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter property description: ");
            String description = scanner.nextLine();

            System.out.print("Enter monthly rental rate: ");
            double rentalRate = Double.parseDouble(scanner.nextLine().trim());

            Property property = rentalController.createProperty(defaultPropertyOwner, propertyId, description, rentalRate);
            if (property != null) {
                properties.put(property.getPropertyId(), property);
                System.out.println("Property created successfully: " + property);
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error: Property identifier and rental rate must be valid numbers.");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Error: " + illegalArgumentException.getMessage());
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public void handleRentProperty() {
        try {
            System.out.print("Enter contract identifier: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter property identifier to rent: ");
            int propertyId = Integer.parseInt(scanner.nextLine().trim());

            Property property = properties.get(propertyId);
            if (property == null) {
                System.out.println("Error: Property not found.");
                return;
            }

            System.out.print("Enter start date (YYYY-MM-DD): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("Enter end date (YYYY-MM-DD): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine().trim());

            RentalContract rentalContract = rentalController.rentProperty(defaultTenant, contractId, property, startDate, endDate);
            if (rentalContract != null) {
                rentalContracts.put(contractId, rentalContract);
                System.out.println("Rental contract created successfully.");
                System.out.println("Property availability is now: " + property.isAvailable());
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error: Contract identifier and property identifier must be valid numbers.");
        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Error: Invalid date format. Please use YYYY-MM-DD.");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Error: " + illegalArgumentException.getMessage());
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public void handleTerminateContract() {
        try {
            System.out.print("Enter contract identifier to terminate: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            RentalContract rentalContract = rentalContracts.get(contractId);
            if (rentalContract == null) {
                System.out.println("Error: Contract not found.");
                return;
            }

            rentalController.terminateContract(rentalContract);
            System.out.println("Contract terminated successfully.");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Error: Contract identifier must be a valid number.");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public Map<Integer, Property> getProperties() {
        return properties;
    }

    public Map<Integer, RentalContract> getRentalContracts() {
        return rentalContracts;
    }

    public PropertyOwner getDefaultPropertyOwner() {
        return defaultPropertyOwner;
    }

    public Tenant getDefaultTenant() {
        return defaultTenant;
    }
}