package az.edu.turing.userprofilemanager.controller;

import az.edu.turing.userprofilemanager.domain.dto.ProfileDto;
import az.edu.turing.userprofilemanager.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        public ResponseEntity<String> createProfile(@RequestBody ProfileDto profileDto) {
            logger.info("Creating new profile: {}", profileDto);
            profileService.save(profileDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Profile created successfully.");
        }

        @GetMapping
        public ResponseEntity<List<ProfileDto>> getProfiles() {
            logger.info("Fetching all profiles");
            List<ProfileDto> profiles = profileService.findAll();
            return ResponseEntity.ok(profiles);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long id) {
            logger.info("Fetching profile with ID: {}", id);
            Optional<ProfileDto> profileDto = profileService.findById(id);
            return profileDto.map(profile -> {
                logger.info("Profile found: {}", profile);
                return ResponseEntity.ok(profile);
            }).orElseGet(() -> {
                logger.warn("Profile with ID: {} not found.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            });
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable Long id) {
            logger.info("Updating profile with ID: {}", id);
            profileDto.setId(id); // Ensure the ID is set in the DTO
            profileService.update(profileDto);
            return ResponseEntity.ok("Profile updated successfully.");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteProfileById(@PathVariable Long id) {
            logger.info("Deleting profile with ID: {}", id);
            profileService.deleteById(id);
            return ResponseEntity.ok("Profile deleted successfully.");
        }

        @DeleteMapping
        public ResponseEntity<String> deleteAllProfiles() {
            logger.info("Deleting all profiles");
            profileService.deleteAll();
            return ResponseEntity.ok("All profiles deleted successfully.");
        }
    }


//    private final ProfileService profileService;
//    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
//
//    @PostMapping
//    public void createProfile(@RequestBody ProfileDto profileDto) {
//        logger.info("Creating new profile: {}", profileDto);
//        profileService.save(profileDto);
//    }
//
//    @GetMapping
//    public List<ProfileDto> getProfiles() {
//        logger.info("Fetching all profiles");
//        return profileService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<ProfileDto> getProfileById(@PathVariable Long id) {
//        logger.info("Fetching profile with ID: {}", id);
//        return profileService.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public void updateProfile(@RequestBody ProfileDto profileDto) {
//        logger.info("Updating profile with ID: {}", profileDto.getId());
//        profileService.update(profileDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProfileById(@PathVariable Long id) {
//        logger.info("Deleting profile with ID: {}", id);
//        profileService.deleteById(id);
//    }
//
//    @DeleteMapping
//    public void deleteAllProfiles() {
//        logger.info("Deleting all profiles");
//        profileService.deleteAll();
//    }


