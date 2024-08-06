package az.edu.turing.userprofilemanager.service;

import az.edu.turing.userprofilemanager.domain.dto.ProfileDto;
import az.edu.turing.userprofilemanager.domain.dto.UserDto;
import az.edu.turing.userprofilemanager.domain.entity.User;
import az.edu.turing.userprofilemanager.domain.repository.ProfileRepository;
import az.edu.turing.userprofilemanager.domain.entity.Profile;
import az.edu.turing.userprofilemanager.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setCreatAt(userDto.getCreatAt());
        user.setUpdateAt(userDto.getUpdateAt());
        user.setProfilePhoto(userDto.getProfilePhoto());

        if (userDto.getProfile() != null) {
            Profile profile = new Profile();
            profile.setId(userDto.getProfile().getId());
            profile.setAddress(userDto.getProfile().getAddress());
            profile.setPhoneNumber(userDto.getProfile().getPhoneNumber());
            user.setProfile(profile);
            profile.setUser(user);
        }

        userRepository.save(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getAge(),
                        user.getCreatAt(),
                        user.getUpdateAt(),
                        user.getProfilePhoto(),
                        user.getProfile() != null ? new ProfileDto(
                                user.getProfile().getId(),
                                user.getProfile().getAddress(),
                                user.getProfile().getPhoneNumber()
                        ) : null))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getAge(),
                        user.getCreatAt(),
                        user.getUpdateAt(),
                        user.getProfilePhoto(),
                        user.getProfile() != null ? new ProfileDto(
                                user.getProfile().getId(),
                                user.getProfile().getAddress(),
                                user.getProfile().getPhoneNumber()
                        ) : null));
    }

    @Override
    public UserDto update(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setAge(userDto.getAge());
            user.setUpdateAt(new java.util.Date());
            user.setProfilePhoto(userDto.getProfilePhoto());

            if (userDto.getProfile() != null) {
                Profile profile = new Profile();
                profile.setId(userDto.getProfile().getId());
                profile.setAddress(userDto.getProfile().getAddress());
                profile.setPhoneNumber(userDto.getProfile().getPhoneNumber());
                user.setProfile(profile);
                profile.setUser(user);
            }

            userRepository.save(user);
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getAge(),
                    user.getCreatAt(),
                    user.getUpdateAt(),
                    user.getProfilePhoto(),
                    user.getProfile() != null ? new ProfileDto(
                            user.getProfile().getId(),
                            user.getProfile().getAddress(),
                            user.getProfile().getPhoneNumber()
                    ) : null);
        } else {
            return null;
        }
    }

    @Override
    public UserDto updateAge(UserDto userDto, Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAge(userDto.getAge());
            user.setUpdateAt(new java.util.Date());
            userRepository.save(user);
            return new UserDto(
                    user.getId(),
                    user.getUsername(),
                    user.getAge(),
                    user.getCreatAt(),
                    user.getUpdateAt(),
                    user.getProfilePhoto(),
                    user.getProfile() != null ? new ProfileDto(
                            user.getProfile().getId(),
                            user.getProfile().getAddress(),
                            user.getProfile().getPhoneNumber()
                    ) : null);
        } else {
            return null;
        }
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Optional<UserDto> findByProfileId(Long profileId) {
        return profileRepository.findById(profileId)
                .map(profile -> {
                    User user = profile.getUser();
                    return new UserDto(
                            user.getId(),
                            user.getUsername(),
                            user.getAge(),
                            user.getCreatAt(),
                            user.getUpdateAt(),
                            user.getProfilePhoto(),
                            new ProfileDto(
                                    profile.getId(),
                                    profile.getAddress(),
                                    profile.getPhoneNumber()
                            ));
                });
    }
}
