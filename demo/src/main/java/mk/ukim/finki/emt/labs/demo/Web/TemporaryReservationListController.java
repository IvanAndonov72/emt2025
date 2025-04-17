package mk.ukim.finki.emt.labs.demo.Web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.labs.demo.Model.DTO.AddAccommodationToReservationDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayReservationDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayUserDto;
import mk.ukim.finki.emt.labs.demo.Service.TemporaryReservationListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
public class TemporaryReservationListController {

    private final TemporaryReservationListService reservationService;

    public TemporaryReservationListController(TemporaryReservationListService reservationService) {
        this.reservationService = reservationService;
    }

    @Operation(
            summary = "Add accommodation to reservation list",
            description = "Adds an accommodation to the user's temporary reservation list if it is available."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully"),
            @ApiResponse(responseCode = "400", description = "Accommodation is not available"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<DisplayReservationDto> addToReservationList(
            @RequestBody AddAccommodationToReservationDto dto,
            HttpServletRequest request
    ) throws Exception {
        DisplayUserDto user = (DisplayUserDto) request.getSession().getAttribute("user");
        return ResponseEntity.ok(reservationService.addAccommodationToReservation(user.username(), dto.accommodationId()));
    }

    @Operation(
            summary = "View reservation list",
            description = "Returns the current list of accommodations in the user's reservation list"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation list retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "User not authenticated")
    })
    @GetMapping
    public ResponseEntity<DisplayReservationDto> viewReservationList(HttpServletRequest request) throws Exception {
        DisplayUserDto user = (DisplayUserDto) request.getSession().getAttribute("user");
        return ResponseEntity.ok(reservationService.getReservationList(user.username()));
    }

    @Operation(
            summary = "Confirm reservations",
            description = "Marks all accommodations in the user's reservation list as rented"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All reservations confirmed"),
            @ApiResponse(responseCode = "401", description = "User not authenticated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/confirm")
    public ResponseEntity<DisplayReservationDto> confirmReservations(HttpServletRequest request) throws Exception {
        DisplayUserDto user = (DisplayUserDto) request.getSession().getAttribute("user");
        return ResponseEntity.ok(reservationService.confirmReservations(user.username()));
    }
}
