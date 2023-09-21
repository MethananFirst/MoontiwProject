package ssi2.moontiw_announcement.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ssi2.moontiw_announcement.entities.Categories;
import ssi2.moontiw_announcement.repositories.AnnouncementRepository;
import ssi2.moontiw_announcement.repositories.CategoryRepository;
import ssi2.moontiw_announcement.utils.ListMapper;

@Service
public class CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Categories getCategoryById(int categoryId) {return categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category id "+ categoryId + "does not exist"));}
}
