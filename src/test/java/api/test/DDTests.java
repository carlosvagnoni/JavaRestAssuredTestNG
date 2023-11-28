package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Contains Data-Driven Tests (DDT) for user-related API endpoints using TestNG.
 * These tests utilize data providers to perform operations like user creation, retrieval, and deletion.
 */
public class DDTests {
    public Logger logger;

    @BeforeClass
    public void setup() {
        // Initialize logger
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String firstName, String lastName, String userEmail, String password, String phoneNumber) {
        logger.info("DDTest: Create User");

        // Create a User object with provided data
        User userPayload = new User();
        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(userEmail);
        userPayload.setPassword(password);
        userPayload.setPhone(phoneNumber);

        // Send a POST request to create a user
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        // Assertions for the POST request
        Assert.assertEquals(response.getStatusCode(), 200);
        String messageValue = response.jsonPath().getString("message");
        Assert.assertEquals(messageValue, userID);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testGetUserByUserName(String userName) {
        logger.info("DDTest: Read User");

        // Send a GET request to retrieve user details by username
        Response response = UserEndPoints.readUser(userName);
        response.then().log().all();

        // Assertions for the GET request
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getContentType().contains("application/json"));
        String usernameValue = response.jsonPath().getString("username");
        Assert.assertEquals(usernameValue, userName);
    }

    @Test(priority = 3, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String userName) {
        logger.info("DDTest: Delete User");

        // Send a DELETE request to delete a user by username
        Response response = UserEndPoints.deleteUser(userName);
        response.then().log().all();

        // Assertions for the DELETE request
        Assert.assertEquals(response.getStatusCode(), 200);
        String messageValue = response.jsonPath().getString("message");
        Assert.assertEquals(messageValue, userName);
    }
}
