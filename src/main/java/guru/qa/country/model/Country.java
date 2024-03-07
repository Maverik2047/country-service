package guru.qa.country.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.country.data.CountryEntity;

public record Country(@JsonProperty("country_name")
                      String countryName,
                      @JsonProperty("country_code")
                      String countryCode
) {

    public static Country countryFromEntity(CountryEntity ce) {
        return new Country(
                ce.getCountryName(),
                ce.getCountryCode()
        );
    }

    public static CountryEntity fromJson(Country country) {

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryCode(country.countryCode);
        countryEntity.setCountryName(country.countryName);
        return countryEntity;
    }
}
