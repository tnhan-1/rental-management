package rental;

public class Property {
    private int propertyId;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    public Property(int propertyId, String description, double rentalRate) {
        validatePropertyId(propertyId);
        validateDescription(description);
        validateRentalRate(rentalRate);

        this.propertyId = propertyId;
        this.description = description.trim();
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    public void updateDescription(String description) {
        validateDescription(description);
        this.description = description.trim();
    }

    public void updateRentalRate(double rentalRate) {
        validateRentalRate(rentalRate);
        this.rentalRate = rentalRate;
    }

    public int getPropertyId() {
        return this.propertyId;
    }

    public String getDescription() {
        return this.description;
    }

    public double getRentalRate() {
        return this.rentalRate;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return String.format("Property [propertyId=%d, description='%s', rentalRate=%.2f, isAvailable=%b]",
                propertyId, description, rentalRate, isAvailable);
    }

    private static void validatePropertyId(int propertyId) {
        if (propertyId <= 0) {
            throw new IllegalArgumentException("PropertyID must be a positive integer.");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description must be nonempty after trimming.");
        }
    }

    private static void validateRentalRate(double rentalRate) {
        if (!Double.isFinite(rentalRate) || rentalRate <= 0) {
            throw new IllegalArgumentException("RentalRate must be a finite, positive decimal number.");
        }
    }
}