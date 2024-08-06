package az.edu.turing.userprofilemanager.service;


import az.edu.turing.userprofilemanager.domain.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void save(UserDto userDto);
    List<UserDto> findAll();
    void deleteAll();
    void deleteById(Long id);
    Optional<UserDto> findById(Long id);
    UserDto update(UserDto userDto);
    UserDto updateAge(UserDto userDto, Long id);
    long count();
    Optional<UserDto> findByProfileId(Long profileId);
}
