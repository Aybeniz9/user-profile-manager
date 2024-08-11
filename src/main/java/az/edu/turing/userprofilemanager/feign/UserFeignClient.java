package az.edu.turing.userprofilemanager.feign;

import az.edu.turing.userprofilemanager.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserFeignClient {

    @PostMapping
    ResponseEntity<String> createUser(@RequestBody UserDto userDto);

    @GetMapping
    ResponseEntity<List<UserDto>> getUsers();

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id);

    @GetMapping("/profile/{profileId}")
    ResponseEntity<UserDto> getUserByProfileId(@PathVariable("profileId") Long profileId);

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long id);

    @GetMapping("/count")
    ResponseEntity<Long> getUserCount();

    @PatchMapping("/{id}/age")
    ResponseEntity<UserDto> updateUserAge(@RequestBody UserDto userDto, @PathVariable("id") Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable("id") Long id);

    @DeleteMapping
    ResponseEntity<String> deleteAllUsers();
}

