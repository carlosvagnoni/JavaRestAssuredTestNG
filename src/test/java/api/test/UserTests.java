package api.test;
import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

/**
 * Contains test methods to perform CRUD operations on user endpoints using generated/fake data.
 */
public class UserTests {

    Faker faker;
    User userPayload;

    public Logger logger;

    @BeforeClass
    public void setup()
    {
        faker = new Faker();
        userPayload = new User();

        // Create a User object with generated fake data
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        //Logs
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser()
    {
        logger.info("Test: Create User");
        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        String messageValue = response.jsonPath().getString("message");
        Assert.assertEquals(messageValue, String.valueOf(userPayload.getId()));
    }

    @Test(priority = 2)
    public void testGetUserByUserName()
    {
        logger.info("Test: Read User");
        Response response = UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getContentType().contains("application/json"));
        String usernameValue = response.jsonPath().getString("username");
        Assert.assertEquals(usernameValue, userPayload.getUsername());
        String userIdValue = response.jsonPath().getString("id");
        Assert.assertEquals(userIdValue, String.valueOf(userPayload.getId()));
    }

    @Test(priority = 3)
    public void testUpdateUserByName()
    {
        logger.info("Test: Update User");

        //Update data using payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(), 200);
        String messageValue1 = response.jsonPath().getString("message");
        Assert.assertEquals(messageValue1, String.valueOf(userPayload.getId()));

        //Checking data after update
        Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        String messageValue2 = responseAfterUpdate.jsonPath().getString("firstName");
        Assert.assertEquals(messageValue2, String.valueOf(userPayload.getFirstName()));

    }

    @Test(priority = 4)
    public void testDeleteUserByName()
    {
        logger.info("Test: Delete User");

        Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        String messageValue = response.jsonPath().getString("message");
        Assert.assertEquals(messageValue, userPayload.getUsername());
    }
}
