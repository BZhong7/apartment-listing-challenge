package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.service.UserService;
import ai.aprillabs.listingapp.web.dto.request.CreateUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequestDto userDto) {
        User user = new User();

        user.setAge(userDto.getAge());
        user.setName(userDto.getName());
        user.setType(userDto.getType());
        user.setNumOfReferral(userDto.getNumOfReferral());

        User createdUser = userService.createUser(user);
        return new ResponseEntity<User>(
                createdUser,
                HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> getUser(@Valid @PathVariable Integer id) {
        return userService.getUser(id)
                .map(user -> new ResponseEntity<>(
                        user,
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path="/{id}/discount")
    public ResponseEntity<Integer> getDiscountRate(CreateUserRequestDto userDto) {
        Integer discountRate = userService.getDiscountRateForUser(userDto.getId());
        return new ResponseEntity<>(
                discountRate,
                HttpStatus.OK);
    }
}
