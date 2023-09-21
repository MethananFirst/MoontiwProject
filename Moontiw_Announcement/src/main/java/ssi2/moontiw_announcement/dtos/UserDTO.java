package ssi2.moontiw_announcement.dtos;

import lombok.Getter;
import lombok.Setter;
import ssi2.moontiw_announcement.entities.Users;

import java.time.ZonedDateTime;


@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String name;
    private String email;
    private String role;
    private ZonedDateTime createdOn;
    private ZonedDateTime updatedOn;

}
