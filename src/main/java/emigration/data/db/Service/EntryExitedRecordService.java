package emigration.data.db.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emigration.data.db.Model.EntryExitedRecord;
import emigration.data.db.Repository.EntryExitedRecordRepository;

@Service
public class EntryExitedRecordService {

    @Autowired
    private EntryExitedRecordRepository entryExitRecordRepository;

    public List<EntryExitedRecord> getAllEntryExitRecords() {
        return entryExitRecordRepository.findAll();
    }

    public EntryExitedRecord getEntryExitRecordById(Long recordId) {
        return entryExitRecordRepository.findById(recordId).orElse(null);
    }

    public EntryExitedRecord saveEntryExitRecord(EntryExitedRecord entryExitRecord) {
        return entryExitRecordRepository.save(entryExitRecord);
    }

    public EntryExitedRecord updateEntryExitRecord(Long recordId, EntryExitedRecord updatedEntryExitRecord) {
        EntryExitedRecord existingEntryExitRecord = entryExitRecordRepository.findById(recordId).orElse(null);
        if (existingEntryExitRecord != null) {
            existingEntryExitRecord.setEntryDate(updatedEntryExitRecord.getEntryDate());
            existingEntryExitRecord.setExitDate(updatedEntryExitRecord.getExitDate());
            return entryExitRecordRepository.save(existingEntryExitRecord);
        }
        return null;
    }

    public void deleteEntryExitRecordById(Long recordId) {
        entryExitRecordRepository.deleteById(recordId);
    }
}
