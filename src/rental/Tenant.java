import java.time.LocalDate;
import java.util.*;

public class Tenant extends User{
    private List<RentalContract> rentalContracts;

    public Tenant(int userId, String firstName, String lastName, String email) {
        super(userId, firstName, lastName, email);
        rentalContracts = new ArrayList<>();
    }

    public RentalContract rentProperty(int contractId, Property property, LocalDate startDate, LocalDate endDate) {
        RentalContract contract = new RentalContract(contractId, property,this, startDate, endDate);
        rentalContracts.add(contract);
        return contract;
    }

    public List<RentalContract> getRentalContracts() {
        return rentalContracts;
    }
}
