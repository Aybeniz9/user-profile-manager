package az.edu.turing.userprofilemanager.controller;

import az.edu.turing.userprofilemanager.domain.dto.ProfileDto;
import az.edu.turing.userprofilemanager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @PostMapping
    public void createProfile(@RequestBody ProfileDto profileDto) {
        logger.info("Creating new profile: {}", profileDto);
        profileService.save(profileDto);
    }

    @GetMapping
    public List<ProfileDto> getProfiles() {
        logger.info("Fetching all profiles");
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProfileDto> getProfileById(@PathVariable Long id) {
        logger.info("Fetching profile with ID: {}", id);
        return profileService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateProfile(@RequestBody ProfileDto profileDto) {
        logger.info("Updating profile with ID: {}", profileDto.getId());
        profileService.update(profileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProfileById(@PathVariable Long id) {
        logger.info("Deleting profile with ID: {}", id);
        profileService.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllProfiles() {
        logger.info("Deleting all profiles");
        profileService.deleteAll();
    }
}

