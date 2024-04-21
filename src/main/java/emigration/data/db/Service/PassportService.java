package emigration.data.db.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emigration.data.db.Model.Passport;
import emigration.data.db.Repository.PassportRepository;

@Service
public class PassportService {

    @Autowired
    private PassportRepository passportRepository;

    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    public Passport getPassportByNumber(String passportNumber) {
        return passportRepository.findById(passportNumber).orElse(null);
    }

    public Passport savePassport(Passport passport) {
        return passportRepository.save(passport);
    }

    public Passport updatePassport(String passportNumber, Passport updatedPassport) {
        Passport existingPassport = passportRepository.findById(passportNumber).orElse(null);
        if (existingPassport != null) {
            existingPassport.setIssueDate(updatedPassport.getIssueDate());
            existingPassport.setExpirationDate(updatedPassport.getExpirationDate());
            existingPassport.setCountryOfIssue(updatedPassport.getCountryOfIssue());
            return passportRepository.save(existingPassport);
        }
        return null;
    }

    public void deletePassportByNumber(String passportNumber) {
        passportRepository.deleteById(passportNumber);
    }
}
