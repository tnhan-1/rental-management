import java.util.ArrayList;
import java.util.List;

// Kế thừa từ User (Không khai báo lại id, name, email...)
public class PropertyOwner extends User {
    private List<Property> listOfProperties;

    public PropertyOwner(String id, String name, String email, String phone) {
        super(id, name, email, phone);
        this.listOfProperties = new ArrayList<>();
    }

    public void addProperty(Property property) {
        this.listOfProperties.add(property);
    }

    public List<Property> getListOfProperties() {
        return listOfProperties;
    }
}