package mk.ukim.finki.emt.labs.demo.Service.implementation;

import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayAccommodationDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayReservationDto;
import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import mk.ukim.finki.emt.labs.demo.Model.domain.TemporaryReservationList;
import mk.ukim.finki.emt.labs.demo.Model.domain.UserEntity;
import mk.ukim.finki.emt.labs.demo.Repository.AccommodationRepository;
import mk.ukim.finki.emt.labs.demo.Repository.TemporaryReservationListRepository;
import mk.ukim.finki.emt.labs.demo.Repository.UserRepository;
import mk.ukim.finki.emt.labs.demo.Service.TemporaryReservationListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemporaryReservationListServiceImpl implements TemporaryReservationListService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final TemporaryReservationListRepository reservationListRepository;

    public TemporaryReservationListServiceImpl(
            UserRepository userRepository,
            AccommodationRepository accommodationRepository,
            TemporaryReservationListRepository reservationListRepository
    ) {
        this.userRepository = userRepository;
        this.accommodationRepository = accommodationRepository;
        this.reservationListRepository = reservationListRepository;
    }

    @Override
    public DisplayReservationDto addAccommodationToReservation(String username, Long accommodationId) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        if (accommodation.getIsRented()) {
            throw new RuntimeException("Accommodation already rented");
        }

        TemporaryReservationList list = user.getReservationList();
        if (list == null) {
            list = new TemporaryReservationList(user);
            user.setReservationList(list);
        }

        if (!list.getAccommodations().contains(accommodation)) {
            list.addAccommodation(accommodation);
            reservationListRepository.save(list);
        }

        return mapToDto(user.getUsername(), list.getAccommodations());
    }

    @Override
    public DisplayReservationDto getReservationList(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TemporaryReservationList list = user.getReservationList();
        List<Accommodation> accommodations = list != null ? list.getAccommodations() : List.of();

        return mapToDto(user.getUsername(), accommodations);
    }

    @Override
    public DisplayReservationDto confirmReservations(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TemporaryReservationList list = user.getReservationList();
        if (list != null) {
            for (Accommodation acc : list.getAccommodations()) {
                if (!acc.getIsRented()) {
                    acc.setRented(true);
                    accommodationRepository.save(acc);
                }
            }
            list.clearAccommodations();
            reservationListRepository.save(list);
        }

        return mapToDto(user.getUsername(), List.of());
    }


    //pogledni uste ednas
    private DisplayReservationDto mapToDto(String username, List<Accommodation> accommodations) {
        List<DisplayAccommodationDto> dtoList = accommodations.stream()
                .map(acc -> new DisplayAccommodationDto(
                        acc.getId(),
                        acc.getName(),
                        acc.getCategory().toString(),
                        acc.getHost().getName() + " " + acc.getHost().getSurname(), // Concatenate host's full name
                        acc.getHost().getCountry().getName(), // Assuming Host has a Country object
                        acc.getNumRooms(),
                        acc.getIsRented() // Assuming this is a boolean value
                ))
                .collect(Collectors.toList());

        return new DisplayReservationDto(username, dtoList);
    }


}
