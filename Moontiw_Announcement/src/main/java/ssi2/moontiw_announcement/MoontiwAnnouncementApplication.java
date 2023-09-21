package ssi2.moontiw_announcement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ssi2.moontiw_announcement.repositories.CustomRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomRepositoryImpl.class)
public class MoontiwAnnouncementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoontiwAnnouncementApplication.class, args);
    }

}
