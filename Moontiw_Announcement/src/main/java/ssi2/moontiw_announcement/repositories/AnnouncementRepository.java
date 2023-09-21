package ssi2.moontiw_announcement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssi2.moontiw_announcement.entities.Announcement;

import java.util.List;


public interface AnnouncementRepository  extends JpaRepository<Announcement, Integer> {
    List<Announcement> findAllByOrderByIdDesc();


}

