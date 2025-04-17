package mk.ukim.finki.emt.labs.demo.Model.DTO;

import java.util.List;

public record DisplayReservationDto(
        String username,
        List<DisplayAccommodationDto> accommodations
) { }

