package mk.ukim.finki.emt.labs.demo.Repository;

import mk.ukim.finki.emt.labs.demo.Model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
}
