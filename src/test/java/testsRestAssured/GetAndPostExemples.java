package testsRestAssured;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExemples {

    @Test
    public void testGet() {
        baseURI = "https://reqres.in/api";
        given()
                .get("/users?page=2")
                .then().statusCode(200)
                .body("data[4].first_name", equalTo("George"))
                .body("data.first_name", hasItems("George", "Byron"));
    }

    @Test
    public void testPost() {
        Map<String, Object> map = new HashMap<>();

//        map.put("name", "Anrei");
//        map.put("job", "SDET");
//        System.out.println(map);

        JSONObject request = new JSONObject(map);

        request.put("name", "Anrei");
        request.put("job", "SDET");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString())
        .when()
                .post("/users")
        .then().statusCode(201).log().all();



    }


}


