package az.edu.turing.userprofilemanager.controller;

import az.edu.turing.userprofilemanager.domain.dto.UserDto;
import az.edu.turing.userprofilemanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        @PostMapping
        public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
            logger.info("Creating new user with username: {}", userDto.getUsername());
            userService.save(userDto);
            logger.info("User created successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
        }

        @GetMapping
        public ResponseEntity<List<UserDto>> getUsers() {
            logger.info("Fetching all users.");
            List<UserDto> users = userService.findAll();
            logger.info("Fetched {} users.", users.size());
            return ResponseEntity.ok(users);
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
            logger.info("Fetching user with ID: {}", id);
            Optional<UserDto> userDto = userService.findById(id);
            return userDto.map(user -> {
                logger.info("User found: {}", user.getUsername());
                return ResponseEntity.ok(user);
            }).orElseGet(() -> {
                logger.warn("User with ID: {} not found.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            });
        }

        @GetMapping("/profile/{profileId}")
        public ResponseEntity<UserDto> getUserByProfileId(@PathVariable Long profileId) {
            logger.info("Fetching user with profile ID: {}", profileId);
            Optional<UserDto> userDto = userService.findByProfileId(profileId);
            return userDto.map(user -> {
                logger.info("User found: {}", user.getUsername());
                return ResponseEntity.ok(user);
            }).orElseGet(() -> {
                logger.warn("User with profile ID: {} not found.", profileId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            });
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateUser(@RequestBody UserDto userDto, @PathVariable Long id) {
            logger.info("Updating user with ID: {}", id);
            userService.update(userDto);
            logger.info("User updated successfully.");
            return ResponseEntity.ok("User updated successfully.");
        }

        @GetMapping("/count")
        public ResponseEntity<Long> getUserCount() {
            logger.info("Fetching user count.");
            long count = userService.count();
            logger.info("Total users count: {}", count);
            return ResponseEntity.ok(count);
        }

        @PatchMapping("/{id}/age")
        public ResponseEntity<UserDto> updateUserAge(@RequestBody UserDto userDto, @PathVariable Long id) {
            logger.info("Updating age for user with ID: {}", id);
            UserDto updatedUser = userService.updateAge(userDto, id);
            logger.info("User age updated successfully for user: {}", updatedUser.getUsername());
            return ResponseEntity.ok(updatedUser);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
            logger.info("Deleting user with ID: {}", id);
            userService.deleteById(id);
            logger.info("User deleted successfully.");
            return ResponseEntity.ok("User deleted successfully.");
        }

        @DeleteMapping
        public ResponseEntity<String> deleteAllUsers() {
            logger.info("Deleting all users.");
            userService.deleteAll();
            logger.info("All users deleted successfully.");
            return ResponseEntity.ok("All users deleted successfully.");
        }
    }


//    private final UserService userService;
//    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//    @PostMapping("/newusers")
//    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
//        logger.info("Creating new user with username: {}", userDto.getUsername());
//        userService.save(userDto);
//        logger.info("User created successfully.");
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
//    }
//
//
//
//    @GetMapping("/all/users")
//    public List<UserDto> getUsers() {
//        logger.info("Fetching all users.");
//        List<UserDto> users = userService.findAll();
//        logger.info("Fetched {} users.", users.size());
//        return users;
//    }
//
//    @GetMapping("/{id}")
//    public Optional<UserDto> getUserById(@PathVariable Long id) {
//        logger.info("Fetching user with ID: {}", id);
//        Optional<UserDto> userDto = userService.findById(id);
//        if (userDto.isPresent()) {
//            logger.info("User found: {}", userDto.get().getUsername());
//        } else {
//            logger.warn("User with ID: {} not found.", id);
//        }
//        return userDto;
//    }
//
//    @GetMapping("/profile/{profileId}")
//    public Optional<UserDto> getUserByProfileId(@PathVariable Long profileId) {
//        logger.info("Fetching user with profile ID: {}", profileId);
//        Optional<UserDto> userDto = userService.findByProfileId(profileId);
//        if (userDto.isPresent()) {
//            logger.info("User found: {}", userDto.get().getUsername());
//        } else {
//            logger.warn("User with profile ID: {} not found.", profileId);
//        }
//        return userDto;
//    }
//
//    @PutMapping("/{id}")
//    public void updateUser(@RequestBody UserDto userDto) {
//        logger.info("Updating user with ID: {}", userDto.getId());
//        userService.update(userDto);
//        logger.info("User updated successfully.");
//    }
//
//    @GetMapping("/count/users")
//    public long getUserCount() {
//        logger.info("Fetching user count.");
//        long count = userService.count();
//        logger.info("Total users count: {}", count);
//        return count;
//    }
//
//    @PatchMapping("/age/{id}")
//    public UserDto updateUserAge(@RequestBody UserDto userDto, @PathVariable Long id) {
//        logger.info("Updating age for user with ID: {}", id);
//        UserDto updatedUser = userService.updateAge(userDto, id);
//        logger.info("User age updated successfully for user: {}", updatedUser.getUsername());
//        return updatedUser;
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable Long id) {
//        logger.info("Deleting user with ID: {}", id);
//        userService.deleteById(id);
//        logger.info("User deleted successfully.");
//    }
//
//    @DeleteMapping("/all")
//    public void deleteAllUsers() {
//        logger.info("Deleting all users.");
//        userService.deleteAll();
//        logger.info("All users deleted successfully.");
//    }


