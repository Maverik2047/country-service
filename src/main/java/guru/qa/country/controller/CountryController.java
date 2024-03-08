package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Country> getAll() {
        return countryService.getAllCountries();
    }

    @PostMapping("/addCountry")
    @ResponseStatus(HttpStatus.CREATED)
    public Country addCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PatchMapping("/editCountryName")
    @ResponseStatus(HttpStatus.OK)
    public Country editCountry(@RequestBody Country country) {
        return countryService.editCountryName(country);
    }
}