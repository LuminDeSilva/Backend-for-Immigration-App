package emigration.data.db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emigration.data.db.Model.Passport;

public interface PassportRepository extends JpaRepository<Passport,String>{
	// can define custom query methods here if needed
}
