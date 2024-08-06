package az.edu.turing.userprofilemanager.domain.dto;


import lombok.*;

@Setter
@Getter

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    private String address;
    private String phoneNumber;
}
