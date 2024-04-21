package emigration.data.db.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "entry_exit_record")
public class EntryExitedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "country_code_entry")
    private Country entryCountry;

    @ManyToOne
    @JoinColumn(name = "country_code_exit")
    private Country exitCountry;

    private LocalDate entryDate;
    private LocalDate exitDate;
    // getters/setters
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Country getEntryCountry() {
		return entryCountry;
	}
	public void setEntryCountry(Country entryCountry) {
		this.entryCountry = entryCountry;
	}
	public Country getExitCountry() {
		return exitCountry;
	}
	public void setExitCountry(Country exitCountry) {
		this.exitCountry = exitCountry;
	}
	public LocalDate getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
	public LocalDate getExitDate() {
		return exitDate;
	}
	public void setExitDate(LocalDate exitDate) {
		this.exitDate = exitDate;
	}
    
}
