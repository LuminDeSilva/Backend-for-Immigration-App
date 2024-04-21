package emigration.data.db.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import emigration.data.db.Model.Person;
import emigration.data.db.Repository.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // Method to retrieve all persons
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Method to retrieve person by id
    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    // Method to save a new person
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    // Method to update an existing person
    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {
            existingPerson.setFirstName(updatedPerson.getFirstName());
            existingPerson.setLastName(updatedPerson.getLastName());
            existingPerson.setDateOfBirth(updatedPerson.getDateOfBirth());
            existingPerson.setNationality(updatedPerson.getNationality());
            existingPerson.setResident(updatedPerson.isResident());
            return personRepository.save(existingPerson);
        }
        return null;
    }

    // Method to delete a person by id
    public void deletePersonById(Long id) {
        personRepository.deleteById(id);
    }
}