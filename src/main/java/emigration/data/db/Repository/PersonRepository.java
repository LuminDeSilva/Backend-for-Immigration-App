package emigration.data.db.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import emigration.data.db.Model.Person; 

@EnableJpaRepositories

public interface PersonRepository extends JpaRepository<Person,Long>{
	// define custom query methods here if needed
	List<Person> findByNationality(String nationality);
}
