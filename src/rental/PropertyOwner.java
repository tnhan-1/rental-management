import java.util.ArrayList;
import java.util.List;

public class PropertyOwner extends User {
    private List<Property> ownedProperties;

    public PropertyOwner(int userId, String firstName, String lastName, String email) {
        super(userId, firstName, lastName, email);
        this.ownedProperties = new ArrayList<>();
    }

    public Property createProperty(int propertyId, String description, double rentalRate) {
        Property property = new Property(propertyId, description, rentalRate);
        ownedProperties.add(property);
        return property;
    }

    public List<Property> getProperties() {
        return ownedProperties;
    }
}