package emigration.data.db.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import emigration.data.db.Model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {
    // can define custom query methods here if needed
}
