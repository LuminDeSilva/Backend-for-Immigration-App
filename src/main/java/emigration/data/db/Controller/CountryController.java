package emigration.data.db.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emigration.data.db.Model.Country;
import emigration.data.db.Repository.CountryRepository;
import emigration.data.db.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/{countryCode}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable(value = "countryCode") String countryCode) {
        Country country = countryRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with code: " + countryCode));
        return ResponseEntity.ok().body(country);
    }

    @PostMapping("/")
    public Country createCountry(@RequestBody Country country) {
        return countryRepository.save(country);
    }

    @PutMapping("/{countryCode}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "countryCode") String countryCode,
                                               @RequestBody Country countryDetails) {
        Country country = countryRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with code: " + countryCode));

        country.setCountryName(countryDetails.getCountryName());
        country.setRegion(countryDetails.getRegion());

        final Country updatedCountry = countryRepository.save(country);
        return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/{countryCode}")
    public Map<String, Boolean> deleteCountry(@PathVariable(value = "countryCode") String countryCode) {
        Country country = countryRepository.findById(countryCode)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with code: " + countryCode));

        countryRepository.delete(country);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
