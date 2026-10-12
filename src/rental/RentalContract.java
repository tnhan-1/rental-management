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
        if(!property.isAvailable() || !endDate.isAfter(startDate)){
            throw new IllegalArgumentException("Property must be available and endDate must be after startDate");
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

    public void terminate(){
        if(!isActive){
            return;
        }

        isActive = false;
        property.setAvailable(true);
    }
}
