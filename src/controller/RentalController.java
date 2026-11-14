package controller;

import java.time.LocalDate;
import rental.Property;
import rental.PropertyOwner;
import rental.RentalContract;
import rental.Tenant;

public class RentalController {

    public Property createProperty(PropertyOwner owner, int id,
                                   String description, double rate) {
        return owner.createProperty(id, description, rate);
    }

    public RentalContract rentProperty(Tenant tenant, int contractId, Property property,
                                       LocalDate startDate, LocalDate endDate) {
        return tenant.rentProperty(contractId, property, startDate, endDate);
    }

    public void terminateContract(RentalContract contract) {
        contract.terminate();
    }
}