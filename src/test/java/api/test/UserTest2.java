package api.test;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest2 {

    Faker faker;
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setup(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void postUser(){
        logger.info("*****Creating User*****");

        Response response = UserEndPoints2.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("*****User Created*****");
    }

    @Test(dependsOnMethods = {"postUser"}, priority = 2)
    public void getUserByName(){
        logger.info("*****Reading User Info*****");

        Response response = UserEndPoints2.getUser(this.userPayload.getUsername());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("*****User Info Read*****");
    }

    @Test(dependsOnMethods = {"postUser"}, priority = 3)
    public void updateUser(){
        logger.info("*****Updating User*****");

        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body().statusCode(200); //chai assertion by rest-assured

        Assert.assertEquals(response.getStatusCode(), 200); //testng assertion

        getUserByName();

        logger.info("*****User Updated*****");
    }

    @Test(dependsOnMethods = {"postUser"}, priority = 4)
    public void deleteUser(){
        logger.info("*****Deleting User*****");

        Response response = UserEndPoints2.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("*****User Deleted*****");
    }
}
