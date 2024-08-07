package az.edu.turing.userprofilemanager.repository;


import az.edu.turing.userprofilemanager.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

