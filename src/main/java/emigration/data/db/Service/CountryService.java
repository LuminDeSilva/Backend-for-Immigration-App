package emigration.data.db.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emigration.data.db.Model.Country;
import emigration.data.db.Repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getCountryByCode(String countryCode) {
        return countryRepository.findById(countryCode).orElse(null);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(String countryCode, Country updatedCountry) {
        Country existingCountry = countryRepository.findById(countryCode).orElse(null);
        if (existingCountry != null) {
            existingCountry.setCountryName(updatedCountry.getCountryName());
            existingCountry.setRegion(updatedCountry.getRegion());
            return countryRepository.save(existingCountry);
        }
        return null;
    }

    public void deleteCountryByCode(String countryCode) {
        countryRepository.deleteById(countryCode);
    }
}
