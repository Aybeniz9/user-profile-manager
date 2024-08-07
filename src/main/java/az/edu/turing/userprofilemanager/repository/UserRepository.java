package az.edu.turing.userprofilemanager.repository;

import az.edu.turing.userprofilemanager.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}

