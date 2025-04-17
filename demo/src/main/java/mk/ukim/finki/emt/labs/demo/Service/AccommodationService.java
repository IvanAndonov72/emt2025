package mk.ukim.finki.emt.labs.demo.Service;

import mk.ukim.finki.emt.labs.demo.Model.DTO.CategoryCountDTO;
import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import mk.ukim.finki.emt.labs.demo.Model.DTO.AccommodationDTO;

import java.util.List;

public interface AccommodationService {
    List<Accommodation> findAll();

    Accommodation create(AccommodationDTO accommodationDTO) throws Exception;

    Accommodation update(Long accommodationId, AccommodationDTO accommodationDTO) throws Exception;

    void delete(Long ID);

    Accommodation markAsRented(Long ID) throws Exception;

    List<CategoryCountDTO> countByCategory();
}
