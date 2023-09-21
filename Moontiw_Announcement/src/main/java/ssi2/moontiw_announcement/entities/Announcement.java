package ssi2.moontiw_announcement.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;



@Entity
@Getter
@Setter

@Table(name = "announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcementId",nullable = false)
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;


    @ManyToOne
    @JoinColumn(name = "announcementCategory")
    private Categories category;


}
