package az.edu.turing.userprofilemanager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private int age;
    private Date creatAt;
    private Date updateAt;
    private byte[] profilePhoto;
    private ProfileDto profile;
}
