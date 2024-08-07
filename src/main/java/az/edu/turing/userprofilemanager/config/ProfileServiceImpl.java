package az.edu.turing.userprofilemanager.config;

//@Service
//@Transactional
//@RequiredArgsConstructor
//public class ProfileServiceImpl implements ProfileService {
//
//    private final ProfileRepository profileRepository;
//
//    @Override
//    public void save(ProfileDto profileDto) {
//        Profile profile = new Profile();
//        profile.setId(profileDto.getId());
//        profile.setAddress(profileDto.getAddress());
//        profile.setPhoneNumber(profileDto.getPhoneNumber());
//
//        profileRepository.save(profile);
//    }
//
//    @Override
//    public List<ProfileDto> findAll() {
//        return profileRepository.findAll().stream()
//                .map(profile -> new ProfileDto(
//                        profile.getId(),
//                        profile.getAddress(),
//                        profile.getPhoneNumber()
//                ))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteAll() {
//        profileRepository.deleteAll();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        profileRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<ProfileDto> findById(Long id) {
//        return profileRepository.findById(id)
//                .map(profile -> new ProfileDto(
//                        profile.getId(),
//                        profile.getAddress(),
//                        profile.getPhoneNumber()
//                ));
//    }
//
//    @Override
//    public ProfileDto update(ProfileDto profileDto) {
//        return profileRepository.findById(profileDto.getId()).map(profile -> {
//            profile.setAddress(profileDto.getAddress());
//            profile.setPhoneNumber(profileDto.getPhoneNumber());
//            profileRepository.save(profile);
//            return profileDto;
//        }).orElse(null);
//    }
//}


