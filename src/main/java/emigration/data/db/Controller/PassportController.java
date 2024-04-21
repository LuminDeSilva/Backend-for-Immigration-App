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

import emigration.data.db.Model.Passport;
import emigration.data.db.Repository.PassportRepository;
import emigration.data.db.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/passports")
public class PassportController {
    @Autowired
    private PassportRepository passportRepository;

    @GetMapping("/{passportNumber}")
    public ResponseEntity<Passport> getPassportByNumber(@PathVariable(value = "passportNumber") String passportNumber) {
        Passport passport = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found with number: " + passportNumber));
        return ResponseEntity.ok().body(passport);
    }

    @PostMapping("/")
    public Passport createPassport(@RequestBody Passport passport) {
        return passportRepository.save(passport);
    }

    @PutMapping("/{passportNumber}")
    public ResponseEntity<Passport> updatePassport(@PathVariable(value = "passportNumber") String passportNumber,
                                               @RequestBody Passport passportDetails) {
        Passport passport = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found with number: " + passportNumber));

        passport.setIssueDate(passportDetails.getIssueDate());
        passport.setExpirationDate(passportDetails.getExpirationDate());
        passport.setCountryOfIssue(passportDetails.getCountryOfIssue());

        final Passport updatedPassport = passportRepository.save(passport);
        return ResponseEntity.ok(updatedPassport);
    }

    @DeleteMapping("/{passportNumber}")
    public Map<String, Boolean> deletePassport(@PathVariable(value = "passportNumber") String passportNumber) {
        Passport passport = passportRepository.findById(passportNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Passport not found with number: " + passportNumber));

        passportRepository.delete(passport);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
