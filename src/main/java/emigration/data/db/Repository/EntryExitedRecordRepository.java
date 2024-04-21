package emigration.data.db.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import emigration.data.db.Model.EntryExitedRecord;

public interface EntryExitedRecordRepository extends JpaRepository<EntryExitedRecord, Long>{
	// Custom query method to find entry exit records by person id
    List<EntryExitedRecord> findByPersonId(Long personId);
}
