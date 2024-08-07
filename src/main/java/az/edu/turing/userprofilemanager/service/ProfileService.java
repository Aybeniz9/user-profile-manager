package az.edu.turing.userprofilemanager.service;

import az.edu.turing.userprofilemanager.feign.ProfileFeignClient;
import az.edu.turing.userprofilemanager.model.dto.ProfileDto;
import az.edu.turing.userprofilemanager.exception.ProfileNotFoundException;
import az.edu.turing.userprofilemanager.exception.ProfileServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

import org.springframework.web.client.RestClientException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileFeignClient profileFeignClient;

    public void save(ProfileDto profileDto) {
        try {
            profileFeignClient.createProfile(profileDto);
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error creating profile", e);
        }
    }

    public List<ProfileDto> findAll() {
        try {
            return profileFeignClient.getProfiles().getBody();
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error fetching profiles", e);
        }
    }

    public void deleteAll() {
        try {
            profileFeignClient.deleteAllProfiles();
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error deleting all profiles", e);
        }
    }

    public void deleteById(Long id) {
        try {
            profileFeignClient.deleteProfileById(id);
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error deleting profile with ID: " + id, e);
        }
    }

    public Optional<ProfileDto> findById(Long id) {
        try {
            ResponseEntity<ProfileDto> response = profileFeignClient.getProfileById(id);
            if (response.getStatusCode().is2xxSuccessful()) {
                return Optional.ofNullable(response.getBody());
            } else {
                return Optional.empty();
            }
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error fetching profile with ID: " + id, e);
        }
    }

    public ProfileDto update(ProfileDto profileDto) {
        try {
            profileFeignClient.updateProfile(profileDto, profileDto.getId());
            return findById(profileDto.getId()).orElseThrow(() ->
                    new ProfileNotFoundException("Profile not found for ID: " + profileDto.getId()));
        } catch (RestClientException e) {
            throw new ProfileServiceException("Error updating profile with ID: " + profileDto.getId(), e);
        }
    }
}

