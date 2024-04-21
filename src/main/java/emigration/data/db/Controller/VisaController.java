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

import emigration.data.db.Model.Visa;
import emigration.data.db.Repository.VisaRepository;
import emigration.data.db.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/visas")
public class VisaController {
    @Autowired
    private VisaRepository visaRepository;

    @GetMapping("/{visaNumber}")
    public ResponseEntity<Visa> getVisaByNumber(@PathVariable(value = "visaNumber") String visaNumber) {
        Visa visa = visaRepository.findById(visaNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Visa not found with number: " + visaNumber));
        return ResponseEntity.ok().body(visa);
    }

    @PostMapping("/")
    public Visa createVisa(@RequestBody Visa visa) {
        return visaRepository.save(visa);
    }

    @PutMapping("/{visaNumber}")
    public ResponseEntity<Visa> updateVisa(@PathVariable(value = "visaNumber") String visaNumber,
                                               @RequestBody Visa visaDetails) {
        Visa visa = visaRepository.findById(visaNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Visa not found with number: " + visaNumber));

        visa.setIssueDate(visaDetails.getIssueDate());
        visa.setExpirationDate(visaDetails.getExpirationDate());
        visa.setType(visaDetails.getType());

        final Visa updatedVisa = visaRepository.save(visa);
        return ResponseEntity.ok(updatedVisa);
    }

    @DeleteMapping("/{visaNumber}")
    public Map<String, Boolean> deleteVisa(@PathVariable(value = "visaNumber") String visaNumber) {
        Visa visa = visaRepository.findById(visaNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Visa not found with number: " + visaNumber));

        visaRepository.delete(visa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
