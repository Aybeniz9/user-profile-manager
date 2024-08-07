package az.edu.turing.userprofilemanager.feign;

import az.edu.turing.userprofilemanager.model.dto.ProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "profile-service", url = "${profile.service.url}")
public interface ProfileFeignClient {
    @PostMapping("/profiles")
    void createProfile(@RequestBody ProfileDto profileDto);

    @GetMapping("/profiles")
    ResponseEntity<List<ProfileDto>> getProfiles();

    @GetMapping("/profiles/{id}")
    ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") Long id);

    @PutMapping("/profiles/{id}")
    ResponseEntity<String> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable("id") Long id);

    @DeleteMapping("/profiles/{id}")
    ResponseEntity<String> deleteProfileById(@PathVariable("id") Long id);

    @DeleteMapping("/profiles")
    ResponseEntity<String> deleteAllProfiles();
}


