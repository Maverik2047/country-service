package guru.qa.country.test;

import dto.Country;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CountryApplicationTests {

    public Faker faker = new Faker(new Locale("en"));

    @BeforeAll
    static void setUP() {
        RestAssured.baseURI = "http://127.0.0.1:8080";
    }

    @Test
    void getCountriesTest() {

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

    @Test
    void addCountryTest() {

        String countryName = faker.country().name();
        String countryCode = faker.country().countryCode2();

        Country country = new Country(countryName, countryCode);

        Country response = RestAssured.given()
                .body(country)
                .contentType(JSON)
                .when()
                .post("/addCountry")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response().as(Country.class);

        assertAll(
                "Check Name and Code",
                () -> assertEquals(countryName, response.countryName()),
                () -> assertEquals(countryCode, response.countryCode()));
    }
}
