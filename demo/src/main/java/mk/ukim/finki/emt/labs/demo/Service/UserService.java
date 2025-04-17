package mk.ukim.finki.emt.labs.demo.Service;

import mk.ukim.finki.emt.labs.demo.Model.DTO.CreateUserDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayUserDto;
import mk.ukim.finki.emt.labs.demo.exception.UserNotFoundException;

public interface UserService {
    void register(CreateUserDto createUserDto);
    DisplayUserDto login(String username, String password) throws UserNotFoundException;
}
