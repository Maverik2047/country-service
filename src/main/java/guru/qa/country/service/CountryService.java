package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {

        return countryRepository.findAll()
                .stream()
                .map(Country::countryFromEntity)
                .collect(Collectors.toList());
    }

    public Country saveCountry(@Nonnull Country country) {

        final String countryName = country.countryName();
        final String countryCode = country.countryCode();

        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setCountryName(countryName);
        countryEntity.setCountryCode(countryCode);

        return Country.countryFromEntity(countryRepository.save(countryEntity));
    }

    public Country addCountry(@Nonnull String countryName, @Nonnull String countryCode) {

        return Country.countryFromEntity(
                countryRepository.save(
                        Country.fromJson(new Country(countryName, countryCode))));
    }

    public Country editCountryName(@Nonnull Country country) {

        CountryEntity countryEntity = countryRepository.findByCountryCode(country.countryCode());

        countryEntity.setCountryName(country.countryName());
        return Country.countryFromEntity(countryRepository.save(countryEntity));
    }
}