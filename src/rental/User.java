package rental;

import java.util.regex.Pattern;

public class User {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    private int userId;
    private String firstName;
    private String lastName;
    private String email;

    public User(int userId, String firstName, String lastName, String email) {
        validateID(userId);
        validateName(firstName, "FirstName");
        validateName(lastName, "LastName");
        validateEmail(email);

        this.userId = userId;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void updateProfile(String firstName, String lastName, String email) {
        // validate name and email before update
        validateName(firstName, "FirstName");
        validateName(lastName, "LastName");
        validateEmail(email);

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim();
    }

    private static void validateID(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("UserID must be a positive integer");
    }

    private static void validateName(String name, String field) {
        if (name == null) {
            throw new IllegalArgumentException(field + " is invalid");
        }
        String s = name.trim();
        if (s.isEmpty()
                || !Character.isLetter(s.charAt(0))
                || !Character.isLetter(s.charAt(s.length() - 1))) {
            throw new IllegalArgumentException(field + " is invalid");
        }
        for (char c : s.toCharArray()) {
            boolean ok = Character.isLetter(c) || c == ' ' || c == '-' || c == '\'';
            if (!ok) {
                throw new IllegalArgumentException(field + " is invalid");
            }
        }
    }

    private static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }
}

    
