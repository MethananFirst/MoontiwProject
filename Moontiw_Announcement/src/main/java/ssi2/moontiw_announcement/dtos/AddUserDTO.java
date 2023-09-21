package ssi2.moontiw_announcement.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ssi2.moontiw_announcement.validated.ValidateUniqueCheck;

import java.time.ZonedDateTime;
@Getter
@Setter
public class AddUserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "must not be blank")
    @Size(min = 1,max = 45 , message = "size must be between 1 and 45")
    @ValidateUniqueCheck
    private String username;
    @NotBlank(message = "must not be blank")
    @Size(min = 8,max = 14 , message = "size must be between 8 and 14")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@*#$%^&+=!])(?=\\S+$).{8,14}" , message = "must be 8-14 characters long, at least 1 of uppercase, lowercase, number and special characters")
    @Column(nullable = false)
    private String password;
    @NotBlank(message = "must not be blank")
    @Size(min = 1,max = 100 , message = "size must be between 1 and 100")
    @ValidateUniqueCheck
    private String name;
    @NotBlank(message = "must not be blank")
    @Email(message = "Email should be valid")
    @Size(min = 1,max = 150 , message = "size must be between 1 and 150")
    @ValidateUniqueCheck
    private String email;
    private String role;
    @Column(nullable = false,insertable = false,updatable = false)
    private ZonedDateTime createdOn;
    @Column(nullable = false,insertable = false,updatable = false)
    private ZonedDateTime updatedOn;

}
