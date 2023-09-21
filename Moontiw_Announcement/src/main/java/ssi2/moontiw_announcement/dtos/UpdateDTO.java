package ssi2.moontiw_announcement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@Getter
@Setter
public class UpdateDTO {
    private int id;
    private String announcementTitle;
    private String announcementDescription;
    @JsonProperty("categoryId")
    private Integer announcementCategoryId;
    private String announcementCategory;
    private ZonedDateTime publishDate;
    private ZonedDateTime closeDate;
    private String announcementDisplay;
}
