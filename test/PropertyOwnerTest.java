const PropertyOwner = require('../../js/PropertyOwner');

describe('PropertyOwner Validation Tests', () => {
    let owner;

    beforeEach(() => {
        owner = new PropertyOwner(1, "Anna", "Nguyen", "anna@example.com");
    });

    test('should append property on valid input', () => {
        const prop = owner.createProperty(101, "Apartment A101", 5000.50);
        expect(prop).not.toBeNull();
        expect(owner.properties.length).toBe(1);
    });

    test('should NOT append property when creation fails', () => {
        const prop = owner.createProperty(101, "Apartment A101", -50);
        expect(prop).toBeNull();
        expect(owner.properties.length).toBe(0);
    });
});