package mk.ukim.finki.emt.labs.demo.Repository;

import mk.ukim.finki.emt.labs.demo.Model.DTO.CategoryCountDTO;
import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

    @Query("SELECT new mk.ukim.finki.emt.labs.demo.Model.DTO.CategoryCountDTO(CONCAT('', a.category), COUNT(a)) " +
            "FROM Accommodation a GROUP BY a.category")
    List<CategoryCountDTO> countByCategory();


}
