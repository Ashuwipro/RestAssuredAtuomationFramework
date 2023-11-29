package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void postUser(String userID, String username, String fname,
                         String lname, String umail, String pwd, String phone){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(umail);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);

        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(dataProvider = "Usernames", dataProviderClass = DataProviders.class, dependsOnMethods = {"postUser"})
    public void deleteUserByName(String username){
        Response response = UserEndPoints.deleteUser(username);

        Assert.assertEquals(response.getStatusCode(), 200);
    }


}
