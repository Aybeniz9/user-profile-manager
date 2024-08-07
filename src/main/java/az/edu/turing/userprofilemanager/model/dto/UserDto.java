package az.edu.turing.userprofilemanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private int age;
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;
    private byte[] profilePhoto;
    private ProfileDto profile;
}
