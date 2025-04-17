package mk.ukim.finki.emt.labs.demo.Web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emt.labs.demo.Model.DTO.CreateUserDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.DisplayUserDto;
import mk.ukim.finki.emt.labs.demo.Model.DTO.UserLoginDto;
import mk.ukim.finki.emt.labs.demo.Service.UserService;
import mk.ukim.finki.emt.labs.demo.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register user", description = "Registers a new user")
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateUserDto createUserDto) {
        userService.register(createUserDto);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "User not found")}
    )
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest httpServletRequest) {
        try {
            DisplayUserDto user = userService.login(userLoginDto.username(), userLoginDto.password());
            httpServletRequest.getSession().setAttribute("user", user);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "User logout", description = "Ends the user session")
    @GetMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
    }

}
