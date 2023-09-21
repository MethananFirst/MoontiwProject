package ssi2.moontiw_announcement.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;



import java.time.ZonedDateTime;

@Validated
@Entity
@Getter
@Setter
@Table(name = "user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    @Column(nullable = false,insertable = false,updatable = false)
    private ZonedDateTime createdOn;
    @Column(nullable = false,insertable = false,updatable = false)
    private ZonedDateTime updatedOn;

}
