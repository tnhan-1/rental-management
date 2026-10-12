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

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getPropertyIdentifier() {
        return this.propertyId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRentalRate() {
        return this.rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        if (rentalRate <= 0) {
            throw new IllegalArgumentException("Rental rate must be greater than zero.");
        }
        this.rentalRate = rentalRate;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", description='" + description + '\'' +
                ", rentalRate=" + rentalRate +
                ", isAvailable=" + isAvailable +
                '}';
    }
}