public class Property {
    private int propertyId;
    private String description;
    private double rentalRate;
    private boolean isAvailable;

    public Property(int propertyId, String description, double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero.");
        }
        this.propertyId = propertyId;
        this.description = description;
        this.rentalRate = rentalRate;
        this.isAvailable = true;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateRentalRate(double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero.");
        }
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
}