package mk.ukim.finki.emt.labs.demo.Model.domain;


import jakarta.persistence.*;
import mk.ukim.finki.emt.labs.demo.Model.enums.UserRole;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;



    public UserEntity() {
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TemporaryReservationList reservationList;

    public TemporaryReservationList getReservationList() {
        return reservationList;
    }

    public void setReservationList(TemporaryReservationList reservationList) {
        this.reservationList = reservationList;
    }


    public UserEntity(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

}
