package az.edu.turing.userprofilemanager.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(updatable = false, nullable = false)
    private Long id;
    private String address;
    private String phoneNumber;

    @OneToOne(mappedBy = "profile")
    private User user;
}
