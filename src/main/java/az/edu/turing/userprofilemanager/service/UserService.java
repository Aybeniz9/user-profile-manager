package az.edu.turing.userprofilemanager.service;

import az.edu.turing.userprofilemanager.feign.UserFeignClient;
import az.edu.turing.userprofilemanager.model.dto.UserDto;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
        private final UserFeignClient userFeignClient;

    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public void save(UserDto userDto) {
            try {
                userFeignClient.createUser(userDto);
            } catch (FeignException e) {
                throw new RuntimeException("Error creating user", e);
            }
        }

        public List<UserDto> findAll() {
            try {
                ResponseEntity<List<UserDto>> response = userFeignClient.getUsers();
                return response.getBody() != null ? response.getBody() : new ArrayList<>();
            } catch (FeignException e) {
                throw new RuntimeException("Error fetching users", e);
            }
        }

        public void deleteAll() {
            try {
                userFeignClient.deleteAllUsers();
            } catch (FeignException e) {

                throw new RuntimeException("Error deleting all users", e);
            }
        }

        public void deleteById(Long id) {
            try {
                userFeignClient.deleteUserById(id);
            } catch (FeignException e) {

                throw new RuntimeException("Error deleting user by ID", e);
            }
        }

        public Optional<UserDto> findById(Long id) {
            try {
                ResponseEntity<UserDto> response = userFeignClient.getUserById(id);
                return Optional.ofNullable(response.getBody());
            } catch (FeignException e) {
                throw new RuntimeException("Error fetching user by ID", e);
            }
        }

        public UserDto update(UserDto userDto) {
            try {
                userFeignClient.updateUser(userDto, userDto.getId());
                return findById(userDto.getId()).orElse(null);
            } catch (FeignException e) {
                // Handle the exception appropriately
                throw new RuntimeException("Error updating user", e);
            }
        }

        public UserDto updateAge(UserDto userDto, Long id) {
            try {
                ResponseEntity<UserDto> response = userFeignClient.updateUserAge(userDto, id);
                return response.getBody();
            } catch (FeignException e) {
                throw new RuntimeException("Error updating user age", e);
            }
        }

        public long count() {
            try {
                ResponseEntity<Long> response = userFeignClient.getUserCount();
                return response.getBody() != null ? response.getBody() : 0;
            } catch (FeignException e) {
                throw new RuntimeException("Error fetching user count", e);
            }
        }

        public Optional<UserDto> findByProfileId(Long profileId) {
            try {
                ResponseEntity<UserDto> response = userFeignClient.getUserByProfileId(profileId);
                return Optional.ofNullable(response.getBody());
            } catch (FeignException e) {
                throw new RuntimeException("Error fetching user by profile ID", e);
            }
        }
    }


//    private final UserRepository userRepository;
//    private final ProfileRepository profileRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
//        this.userRepository = userRepository;
//        this.profileRepository = profileRepository;
//    }
//
//    @Override
//    public void save(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setUsername(userDto.getUsername());
//        user.setAge(userDto.getAge());
//        user.setCreatedAt(userDto.getCreatAt());
//        user.setUpdatedAt(userDto.getUpdateAt());
//        user.setProfilePhoto(userDto.getProfilePhoto());
//
//        if (userDto.getProfile() != null) {
//            Profile profile = new Profile();
//            profile.setId(userDto.getProfile().getId());
//            profile.setAddress(userDto.getProfile().getAddress());
//            profile.setPhoneNumber(userDto.getProfile().getPhoneNumber());
//            user.setProfile(profile);
//            profile.setUser(user);
//        }
//
//        userRepository.save(user);
//    }
//
//    @Override
//    public List<UserDto> findAll() {
//        return userRepository.findAll().stream()
//                .map(user -> new UserDto(
//                        user.getId(),
//                        user.getUsername(),
//                        user.getAge(),
//                        user.getCreatedAt(),
//                        user.getUpdatedAt(),
//                        user.getProfilePhoto(),
//                        user.getProfile() != null ? new ProfileDto(
//                                user.getProfile().getId(),
//                                user.getProfile().getAddress(),
//                                user.getProfile().getPhoneNumber()
//                        ) : null))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteAll() {
//        userRepository.deleteAll();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<UserDto> findById(Long id) {
//        return userRepository.findById(id)
//                .map(user -> new UserDto(
//                        user.getId(),
//                        user.getUsername(),
//                        user.getAge(),
//                        user.getCreatedAt(),
//                        user.getUpdatedAt(),
//                        user.getProfilePhoto(),
//                        user.getProfile() != null ? new ProfileDto(
//                                user.getProfile().getId(),
//                                user.getProfile().getAddress(),
//                                user.getProfile().getPhoneNumber()
//                        ) : null));
//    }
//
//    @Override
//    public UserDto update(UserDto userDto) {
//        Optional<User> optionalUser = userRepository.findById(userDto.getId());
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setUsername(userDto.getUsername());
//            user.setAge(userDto.getAge());
//            user.setUpdatedAt(LocalDateTime.now());
//            user.setProfilePhoto(userDto.getProfilePhoto());
//
//            if (userDto.getProfile() != null) {
//                Profile profile = new Profile();
//                profile.setId(userDto.getProfile().getId());
//                profile.setAddress(userDto.getProfile().getAddress());
//                profile.setPhoneNumber(userDto.getProfile().getPhoneNumber());
//                user.setProfile(profile);
//                profile.setUser(user);
//            }
//
//            userRepository.save(user);
//            return new UserDto(
//                    user.getId(),
//                    user.getUsername(),
//                    user.getAge(),
//                    user.getCreatedAt(),
//                    user.getUpdatedAt(),
//                    user.getProfilePhoto(),
//                    user.getProfile() != null ? new ProfileDto(
//                            user.getProfile().getId(),
//                            user.getProfile().getAddress(),
//                            user.getProfile().getPhoneNumber()
//                    ) : null);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public UserDto updateAge(UserDto userDto, Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setAge(userDto.getAge());
//            user.setUpdatedAt(LocalDateTime.now());
//            userRepository.save(user);
//            return new UserDto(
//                    user.getId(),
//                    user.getUsername(),
//                    user.getAge(),
//                    user.getCreatedAt(),
//                    user.getUpdatedAt(),
//                    user.getProfilePhoto(),
//                    user.getProfile() != null ? new ProfileDto(
//                            user.getProfile().getId(),
//                            user.getProfile().getAddress(),
//                            user.getProfile().getPhoneNumber()
//                    ) : null);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public long count() {
//        return userRepository.count();
//    }
//
//    @Override
//    public Optional<UserDto> findByProfileId(Long profileId) {
//        return profileRepository.findById(profileId)
//                .map(profile -> {
//                    User user = profile.getUser();
//                    return new UserDto(
//                            user.getId(),
//                            user.getUsername(),
//                            user.getAge(),
//                            user.getCreatedAt(),
//                            user.getUpdatedAt(),
//                            user.getProfilePhoto(),
//                            new ProfileDto(
//                                    profile.getId(),
//                                    profile.getAddress(),
//                                    profile.getPhoneNumber()
//                            ));
//                });
//    }
//}
//