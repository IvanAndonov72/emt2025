package mk.ukim.finki.emt.labs.demo.Model.DTO;

import mk.ukim.finki.emt.labs.demo.Model.domain.UserEntity;
import mk.ukim.finki.emt.labs.demo.Model.enums.UserRole;

public record CreateUserDto(String username, String password, UserRole role) {

    public UserEntity toUser() {
        return new UserEntity(username, password, role);
    }
}



