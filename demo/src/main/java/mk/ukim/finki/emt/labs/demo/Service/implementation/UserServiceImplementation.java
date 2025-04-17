package mk.ukim.finki.emt.labs.demo.Service.implementation;


import mk.ukim.finki.emt.labs.demo.Model.DTO.CreateUserDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayUserDto;
import mk.ukim.finki.emt.labs.demo.Model.domain.UserEntity;
import mk.ukim.finki.emt.labs.demo.Repository.UserRepository;
import mk.ukim.finki.emt.labs.demo.Service.UserService;
import mk.ukim.finki.emt.labs.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(CreateUserDto createUserDto) {
        UserEntity user = createUserDto.toUser();
        userRepository.save(user);
    }

    public DisplayUserDto login(String username, String password) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }

        if(!user.get().getPassword().equals(password)) {
            throw new UserNotFoundException();
        }

        return DisplayUserDto.fromUserToDisplayUserDto(user.get());
    }
}
