package ssi2.moontiw_announcement.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssi2.moontiw_announcement.dtos.DetailAnnouncementDTO;
import ssi2.moontiw_announcement.entities.Announcement;
import ssi2.moontiw_announcement.entities.Categories;
import ssi2.moontiw_announcement.repositories.CategoryRepository;
import ssi2.moontiw_announcement.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Categories getAnnouncement(@PathVariable int id) {
        Categories category = service.getCategoryById(id);
        return modelMapper.map(category,  Categories.class);
    }



}
