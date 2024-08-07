package az.edu.turing.userprofilemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "az.edu.turing.userprofilemanager.feign")

public class UserProfileManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProfileManagerApplication.class, args);
    }

}
