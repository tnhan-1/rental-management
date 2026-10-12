import java.util.ArrayList;
import java.util.List;

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

    public void setListOfProperties(List<Property> listOfProperties) {
        this.listOfProperties = listOfProperties;
    }
}