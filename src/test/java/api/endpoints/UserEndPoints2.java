package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints2 {

    static ResourceBundle getURL(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("routes");
        return resourceBundle;
    }

    public static Response createUser(User payload){

        Response response = given()
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .body(payload)
                                    .when().post(getURL().getString("post_url"));
        return response;
    }

    public static Response getUser(String username){
        Response response = given()
                                    .pathParam("username", username)
                                    .when().get(getURL().getString("get_url"));
        return response;
    }

    public static Response updateUser(String username, User payload){
        Response response = given()
                                    .contentType(ContentType.JSON)
                                    .accept(ContentType.JSON)
                                    .pathParam("username", username)
                                    .body(payload)
                                    .when().put(getURL().getString("update_url"));
        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                                    .pathParam("username", username)
                                    .when().delete(getURL().getString("delete_url"));
        return response;
    }
}
