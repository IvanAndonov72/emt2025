package mk.ukim.finki.emt.labs.demo.Model.DTO;

public record DisplayAccommodationDto(
        Long id,
        String name,
        String category,
        String hostFullName,
        String country,
        int numRooms,
        boolean isRented
) { }
