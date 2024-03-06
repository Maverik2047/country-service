package guru.qa.country.test;

import dto.Country;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CountryApplicationTests {

    @BeforeAll
    static void setUP() {
        RestAssured.baseURI = "http://127.0.0.1:8080";
    }

    @Test
    void contextLoads() {

        given()
                .when()
                .get("/countries")
                .then()
                .log().body()
                .statusCode(200)
                .extract()
                .response()
                .as(Country[].class);
    }
}
