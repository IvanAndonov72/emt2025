package mk.ukim.finki.emt.labs.demo.Repository;

import mk.ukim.finki.emt.labs.demo.Model.domain.TemporaryReservationList;
import mk.ukim.finki.emt.labs.demo.Model.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryReservationListRepository extends JpaRepository<TemporaryReservationList, Long> {
    Optional<TemporaryReservationList> findByUser(UserEntity user);
}
