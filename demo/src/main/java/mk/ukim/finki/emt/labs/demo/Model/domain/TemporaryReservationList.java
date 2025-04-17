package mk.ukim.finki.emt.labs.demo.Model.domain;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TemporaryReservationList {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private UserEntity user;

    @ManyToMany
    private List<Accommodation> accommodations = new ArrayList<>();

    public TemporaryReservationList() {}

    public TemporaryReservationList(UserEntity user) {
        this.user = user;
    }

    public Long getId() { return id; }

    public List<Accommodation> getAccommodations() { return accommodations; }

    public void addAccommodation(Accommodation accommodation) {
        accommodations.add(accommodation);
    }

    public void clearAccommodations() {
        accommodations.clear();
    }

    public UserEntity getUser() { return user; }

    public void setUser(UserEntity user) { this.user = user; }
}
