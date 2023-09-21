package ssi2.moontiw_announcement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ssi2.moontiw_announcement.entities.Categories;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}
