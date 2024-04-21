package emigration.data.db.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emigration.data.db.Model.Visa;
import emigration.data.db.Repository.VisaRepository;

@Service
public class VisaService {

    @Autowired
    private VisaRepository visaRepository;

    public List<Visa> getAllVisas() {
        return visaRepository.findAll();
    }

    public Visa getVisaByNumber(String visaNumber) {
        return visaRepository.findById(visaNumber).orElse(null);
    }

    public Visa saveVisa(Visa visa) {
        return visaRepository.save(visa);
    }

    public Visa updateVisa(String visaNumber, Visa updatedVisa) {
        Visa existingVisa = visaRepository.findById(visaNumber).orElse(null);
        if (existingVisa != null) {
            existingVisa.setIssueDate(updatedVisa.getIssueDate());
            existingVisa.setExpirationDate(updatedVisa.getExpirationDate());
            existingVisa.setType(updatedVisa.getType());
            return visaRepository.save(existingVisa);
        }
        return null;
    }

    public void deleteVisaByNumber(String visaNumber) {
        visaRepository.deleteById(visaNumber);
    }
}
