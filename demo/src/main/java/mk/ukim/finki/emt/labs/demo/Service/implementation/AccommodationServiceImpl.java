package mk.ukim.finki.emt.labs.demo.Service.implementation;

import mk.ukim.finki.emt.labs.demo.Model.DTO.CategoryCountDTO;
import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import mk.ukim.finki.emt.labs.demo.Model.DTO.AccommodationDTO;
import mk.ukim.finki.emt.labs.demo.Model.domain.Host;
import mk.ukim.finki.emt.labs.demo.Repository.AccommodationRepository;
import mk.ukim.finki.emt.labs.demo.Repository.HostRepository;
import mk.ukim.finki.emt.labs.demo.Service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation create(AccommodationDTO accommodationDTO) throws Exception {
        Host host_obj = hostRepository.findById(accommodationDTO.getHostId()).orElseThrow(Exception::new);
        Accommodation accommodation_obj = new Accommodation(accommodationDTO.getName(),accommodationDTO.getCategory(),host_obj,accommodationDTO.getNumRooms());
        return accommodationRepository.save(accommodation_obj);
    }

    @Override
    public Accommodation update(Long accommodationId, AccommodationDTO accommodationDTO) throws Exception {
        Accommodation accommodation_obj = accommodationRepository.findById(accommodationId).orElseThrow(Exception::new);
        Host host_obj = hostRepository.findById(accommodationDTO.getHostId()).orElseThrow( Exception::new);

        accommodation_obj.setName(accommodationDTO.getName());
        accommodation_obj.setCategory(accommodationDTO.getCategory());
        accommodation_obj.setNumRooms(accommodationDTO.getNumRooms());
        accommodation_obj.setHost(host_obj);
        return accommodationRepository.save(accommodation_obj);
    }

    @Override
    public void delete(Long ID) {
        accommodationRepository.deleteById(ID);
    }

    @Override
    public Accommodation markAsRented(Long ID) throws Exception {
        Accommodation accommodation_obj = accommodationRepository.findById(ID).orElseThrow(Exception::new);

        if(accommodation_obj.getNumRooms() > 0){
            accommodation_obj.setNumRooms(accommodation_obj.getNumRooms() - 1);
            return accommodationRepository.save(accommodation_obj);
        }

        throw new RuntimeException("NO MORE ROOMS AVAILABLE TO RENT!");
    }

    @Override
    public List<CategoryCountDTO> countByCategory() {
        return accommodationRepository.countByCategory();
    }

}
