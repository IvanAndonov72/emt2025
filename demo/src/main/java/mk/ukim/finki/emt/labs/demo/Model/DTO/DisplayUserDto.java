package mk.ukim.finki.emt.labs.demo.Model.DTO;

import mk.ukim.finki.emt.labs.demo.Model.domain.UserEntity;
import mk.ukim.finki.emt.labs.demo.Model.enums.UserRole;

public record DisplayUserDto(String username, String password, UserRole role) {

    public static DisplayUserDto fromUserToDisplayUserDto(UserEntity user) {
        return new DisplayUserDto(
                user.getUsername(),
                user.getPassword(),
                user.getRole()
        );
    }
}

