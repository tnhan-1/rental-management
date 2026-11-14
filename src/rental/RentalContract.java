package rental;

import java.time.LocalDate;

public class RentalContract {
    private int contractId;
    private Property property;
    private Tenant tenant;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentAmount;
    private boolean isActive;

    public RentalContract(int contractId, Property property, Tenant tenant, LocalDate startDate, LocalDate endDate) {
        // validate everything before changing any state, so a failed creation leaves Property untouched
        validateId(contractId);
        validateObject(property, "Property");
        validateObject(tenant, "Tenant");
        validateDates(startDate, endDate);
        if (!property.isAvailable()) {
            throw new IllegalArgumentException("Property is not available");
        }

        this.contractId = contractId;
        this.property = property;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentAmount = property.getRentalRate();
        this.isActive = true;
        property.setAvailable(false);
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void terminate() {
        if (!isActive) {
            return;
        }

        isActive = false;
        property.setAvailable(true);
    }

    private static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ContractID must be a positive integer");
        }
    }

    private static void validateObject(Object value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " is invalid");
        }
    }

    private static void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("StartDate is invalid");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("EndDate is invalid");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("EndDate must be later than StartDate");
        }
    }
}
