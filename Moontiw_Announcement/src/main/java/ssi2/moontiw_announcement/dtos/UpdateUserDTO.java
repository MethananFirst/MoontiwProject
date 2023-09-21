package ssi2.moontiw_announcement.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ssi2.moontiw_announcement.validated.ValidateUniqueCheck;
import ssi2.moontiw_announcement.validated.ValidateUpdateUniqueCheck;


@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank(message = "must not be blank")
    @Size(min = 1,max = 45 , message = "size must be between 1 and 45")
    @ValidateUpdateUniqueCheck
    private String username;
    @NotBlank(message = "must not be blank")
    @Size(min = 1,max = 100 , message = "size must be between 1 and 100")
    @ValidateUpdateUniqueCheck
    private String name;
    @NotBlank(message = "must not be blank")
    @Email(message = "Email should be valid")
    @Size(min = 1,max = 150 , message = "size must be between 1 and 150")
    @ValidateUpdateUniqueCheck
    private String email;
    private String role;
}
