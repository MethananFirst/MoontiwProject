package ssi2.moontiw_announcement.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class DetailAnnouncementDTO {
    private Integer id;
    private String announcementTitle;
    private String announcementCategory;
    private String announcementDescription;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
}
