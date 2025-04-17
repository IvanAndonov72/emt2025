package mk.ukim.finki.emt.labs.demo.Web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mk.ukim.finki.emt.labs.demo.Model.DTO.CategoryCountDTO;
import mk.ukim.finki.emt.labs.demo.Model.domain.Accommodation;
import mk.ukim.finki.emt.labs.demo.Model.DTO.AccommodationDTO;
import mk.ukim.finki.emt.labs.demo.Service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
@SecurityRequirement(name = "Authentication")
public class AccommodationRestController {
    private final AccommodationService accommodationService;

    public AccommodationRestController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public ResponseEntity<List<Accommodation>> findAll(){
        return ResponseEntity.ok(accommodationService.findAll());
    }

    @PostMapping("/add-accommodation")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Accommodation> addAccommodation (@RequestBody AccommodationDTO accommodationDTO) throws Exception{
        return ResponseEntity.ok(accommodationService.create(accommodationDTO));
    }

    @PutMapping("/edit-accommodation/{ID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long ID, @RequestBody AccommodationDTO accommodationDTO) throws Exception{
        return ResponseEntity.ok(accommodationService.update(ID, accommodationDTO));
    }

    @DeleteMapping("/delete-accommodation/{ID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long ID){
        accommodationService.delete(ID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/rent-accommodation/{ID}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Accommodation> markAsRented(@PathVariable Long ID) throws Exception{
         return ResponseEntity.ok(accommodationService.markAsRented(ID));
    }

    @GetMapping("/category-counts")
    public ResponseEntity<List<CategoryCountDTO>> getCategoryCounts() {
        return ResponseEntity.ok(accommodationService.countByCategory());
    }


}
