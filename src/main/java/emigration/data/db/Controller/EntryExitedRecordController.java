package emigration.data.db.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import emigration.data.db.Model.EntryExitedRecord;
import emigration.data.db.Service.EntryExitedRecordService;
import emigration.data.db.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/entry-exit-records")
public class EntryExitedRecordController {

    @Autowired
    private EntryExitedRecordService entryExitRecordService;

    @GetMapping("/{recordId}")
    public ResponseEntity<EntryExitedRecord> getEntryExitRecordById(@PathVariable(value = "recordId") Long recordId) {
        EntryExitedRecord entryExitRecord = entryExitRecordService.getEntryExitRecordById(recordId);
        if (entryExitRecord == null) {
            throw new ResourceNotFoundException("Entry Exit Record not found with id: " + recordId);
        }
        return ResponseEntity.ok().body(entryExitRecord);
    }

    @GetMapping("/")
    public List<EntryExitedRecord> getAllEntryExitRecords() {
        return entryExitRecordService.getAllEntryExitRecords();
    }

    @PostMapping("/")
    public ResponseEntity<EntryExitedRecord> createEntryExitRecord(@Validated @RequestBody EntryExitedRecord entryExitRecord) {
        EntryExitedRecord savedEntryExitRecord = entryExitRecordService.saveEntryExitRecord(entryExitRecord);
        return new ResponseEntity<>(savedEntryExitRecord, HttpStatus.CREATED);
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<EntryExitedRecord> updateEntryExitRecord(@PathVariable(value = "recordId") Long recordId,
                                               @Validated @RequestBody EntryExitedRecord entryExitRecordDetails) {
        EntryExitedRecord existingEntryExitRecord = entryExitRecordService.getEntryExitRecordById(recordId);
        if (existingEntryExitRecord == null) {
            throw new ResourceNotFoundException("Entry Exit Record not found with id: " + recordId);
        }

        existingEntryExitRecord.setEntryDate(entryExitRecordDetails.getEntryDate());
        existingEntryExitRecord.setExitDate(entryExitRecordDetails.getExitDate());

        final EntryExitedRecord updatedEntryExitRecord = entryExitRecordService.saveEntryExitRecord(existingEntryExitRecord);
        return ResponseEntity.ok(updatedEntryExitRecord);
    }

    @DeleteMapping("/{recordId}")
    public Map<String, Boolean> deleteEntryExitRecord(@PathVariable(value = "recordId") Long recordId) {
        EntryExitedRecord existingEntryExitRecord = entryExitRecordService.getEntryExitRecordById(recordId);
        if (existingEntryExitRecord == null) {
            throw new ResourceNotFoundException("Entry Exit Record not found with id: " + recordId);
        }

        entryExitRecordService.deleteEntryExitRecordById(recordId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
