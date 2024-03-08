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

        Country country = new Country(faker.country().name(), faker.country().countryCode2());

        Country response = given()
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
                () -> assertEquals(country.countryName(), response.countryName()),
                () -> assertEquals(country.countryCode(), response.countryCode()));
    }

    @Test
    void addCountryWithoutParamsTest() {

        Country country = new Country(null, null);

        given()
                .body(country)
                .contentType(JSON)
                .when()
                .post("/addCountry")
                .then()
                .log().all()
                .statusCode(500)
                .extract()
                .response().as(Country.class);
    }

    @Test
    void editCountryTest() {

        Country country = new Country(faker.country().name(), "ki");

        Country response = given()
                .body(country)
                .contentType(JSON)
                .when()
                .patch("/editCountryName")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response().as(Country.class);

        assertEquals(country.countryName(), response.countryName());
    }
}