package az.edu.turing.userprofilemanager.controller;

import az.edu.turing.userprofilemanager.domain.dto.UserDto;
import az.edu.turing.userprofilemanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newusers")
    public void createUser(@RequestBody UserDto userDto) {
        logger.info("Creating new user with username: {}", userDto.getUsername());
        userService.save(userDto);
        logger.info("User created successfully.");
    }

    @GetMapping("/all/users")
    public List<UserDto> getUsers() {
        logger.info("Fetching all users.");
        List<UserDto> users = userService.findAll();
        logger.info("Fetched {} users.", users.size());
        return users;
    }

    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        Optional<UserDto> userDto = userService.findById(id);
        if (userDto.isPresent()) {
            logger.info("User found: {}", userDto.get().getUsername());
        } else {
            logger.warn("User with ID: {} not found.", id);
        }
        return userDto;
    }

    @GetMapping("/profile/{profileId}")
    public Optional<UserDto> getUserByProfileId(@PathVariable Long profileId) {
        logger.info("Fetching user with profile ID: {}", profileId);
        Optional<UserDto> userDto = userService.findByProfileId(profileId);
        if (userDto.isPresent()) {
            logger.info("User found: {}", userDto.get().getUsername());
        } else {
            logger.warn("User with profile ID: {} not found.", profileId);
        }
        return userDto;
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody UserDto userDto) {
        logger.info("Updating user with ID: {}", userDto.getId());
        userService.update(userDto);
        logger.info("User updated successfully.");
    }

    @GetMapping("/count/users")
    public long getUserCount() {
        logger.info("Fetching user count.");
        long count = userService.count();
        logger.info("Total users count: {}", count);
        return count;
    }

    @PatchMapping("/age/{id}")
    public UserDto updateUserAge(@RequestBody UserDto userDto, @PathVariable Long id) {
        logger.info("Updating age for user with ID: {}", id);
        UserDto updatedUser = userService.updateAge(userDto, id);
        logger.info("User age updated successfully for user: {}", updatedUser.getUsername());
        return updatedUser;
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteById(id);
        logger.info("User deleted successfully.");
    }

    @DeleteMapping("/all")
    public void deleteAllUsers() {
        logger.info("Deleting all users.");
        userService.deleteAll();
        logger.info("All users deleted successfully.");
    }
}

