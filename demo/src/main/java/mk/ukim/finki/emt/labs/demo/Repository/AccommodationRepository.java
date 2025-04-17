package mk.ukim.finki.emt.labs.demo.Repository;

import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
}
