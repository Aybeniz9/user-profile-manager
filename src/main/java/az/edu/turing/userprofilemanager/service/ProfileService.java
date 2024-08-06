package az.edu.turing.userprofilemanager.service;

import az.edu.turing.userprofilemanager.domain.dto.ProfileDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
    void save(ProfileDto profileDto);
    List<ProfileDto> findAll();
    void deleteAll();
    void deleteById(Long id);
    Optional<ProfileDto> findById(Long id);
    ProfileDto update(ProfileDto profileDto);
}
