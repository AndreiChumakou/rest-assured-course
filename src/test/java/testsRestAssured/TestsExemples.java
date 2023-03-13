package testsRestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import org.hamcrest.Matcher;
//import static io.restassured.RestAssured.given;
//import static org.apache.commons.codec.digest.UnixCrypt.body;
//import static org.hamcrest.Matchers.equalTo;

public class TestsExemples {

    @Test
    public void testOne() {
        String path = "https://reqres.in/api/users?page=2";
        Response response = get(path);

        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testTwo() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2")
                .then().statusCode(200)
                .body("data[1].id", equalTo(8)).log().all();
        given()
                .get("/users?page=2")
                .then().statusCode(200)
                .log().all();
    }

}
