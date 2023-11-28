package api.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user entity for interaction with the API.
 * This class models user data including their ID, username, name, email, password, phone, and user status.
 * Lombok annotations (@Getter and @Setter) auto-generate getter and setter methods for class fields.
 */
@Getter
@Setter
public class User {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    int userStatus = 0;
}
