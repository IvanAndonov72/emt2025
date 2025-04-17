package mk.ukim.finki.emt.labs.demo.Service;

import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayReservationDto;

public interface TemporaryReservationListService {
    DisplayReservationDto addAccommodationToReservation(String username, Long accommodationId);
    DisplayReservationDto getReservationList(String username);
    DisplayReservationDto confirmReservations(String username);
}
