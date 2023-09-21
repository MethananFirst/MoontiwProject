package ssi2.moontiw_announcement.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ssi2.moontiw_announcement.dtos.*;
import ssi2.moontiw_announcement.entities.Announcement;
import ssi2.moontiw_announcement.entities.Categories;
import ssi2.moontiw_announcement.exception.FieldErrorDto;
import ssi2.moontiw_announcement.exception.ValidationException;
import ssi2.moontiw_announcement.repositories.AnnouncementRepository;
import ssi2.moontiw_announcement.repositories.CategoryRepository;
import ssi2.moontiw_announcement.services.AnnouncementService;
import ssi2.moontiw_announcement.utils.ListMapper;

import java.security.URIParameter;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnnouncementService service;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;


    @GetMapping("/admin")
    public List<AnnouncementDTO> getAllAnnouncement() {
        List<Announcement> announcementList = service.getAllAnnouncement();
        List<AnnouncementDTO> announcementDTOList =
                announcementList.stream().map(c -> modelMapper.map(c, AnnouncementDTO.class)).collect(Collectors.toList());
        return announcementDTOList;
    }


    @GetMapping("/{id}")
    public DetailAnnouncementDTO getAnnouncement(@PathVariable int id) {
        Announcement announcement = service.getAnnouncementById(id);
        return modelMapper.map(announcement, DetailAnnouncementDTO.class);
    }











//    @PostMapping("")
//    @ResponseStatus(HttpStatus.OK)
//    public UpdateDTO addNewAnnouncement(@RequestBody UpdateDTO updateDTO) {
//        if(updateDTO.getAnnouncementTitle() == null){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be null"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementTitle().length() <1 || updateDTO.getAnnouncementTitle().length() >200){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementDescription() == null){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be null"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementDescription().length() <1 || updateDTO.getAnnouncementDescription().length() >10000){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementTitle().trim().length() <1 || updateDTO.getAnnouncementTitle().trim().length() >200){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementDisplay() == null ) {
//            updateDTO.setAnnouncementDisplay(updateDTO.getAnnouncementDisplay()==null?"N": updateDTO.getAnnouncementDisplay());
//            return modelMapper.map(service.addNewAnnouncement(updateDTO), UpdateDTO.class);
//        }
//        else if(updateDTO.getAnnouncementDescription().trim() == "" ){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementCategoryId() == null){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("categoryId", "must not be null"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementCategoryId() != 1 && updateDTO.getAnnouncementCategoryId() != 2 && updateDTO.getAnnouncementCategoryId() != 3 && updateDTO.getAnnouncementCategoryId() != 4){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("categoryId", "does not exists"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(!updateDTO.getAnnouncementDisplay().equals("Y") && !updateDTO.getAnnouncementDisplay().equals("N")){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDisplay", "must be either 'Y' or 'N'"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getAnnouncementDisplay().trim() == "" ) {
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDisplay", "must be either 'Y' or 'N'"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateDTO.getPublishDate()!=null && updateDTO.getCloseDate()!=null){
//            if (updateDTO.getPublishDate().isBefore(ZonedDateTime.now())){
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
//                throw new ValidationException(fieldErrors);
//            }
//            if (updateDTO.getCloseDate().equals(updateDTO.getPublishDate())) {
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
//                throw new ValidationException(fieldErrors);
//            }
//            if (updateDTO.getCloseDate().isBefore(updateDTO.getPublishDate())) {
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
//                throw new ValidationException(fieldErrors);
//            }
//            else{
//                return modelMapper.map(service.addNewAnnouncement(updateDTO), UpdateDTO.class);
//            }
//
//        }
//        else if (updateDTO.getPublishDate()!=null && updateDTO.getCloseDate()==null && updateDTO.getPublishDate().isBefore(ZonedDateTime.now())) {
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if (updateDTO.getPublishDate()==null && updateDTO.getCloseDate()!=null && updateDTO.getCloseDate().isBefore(ZonedDateTime.now())) {
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("closeDate", "must be a future date"));
//            throw new ValidationException(fieldErrors);
//        }
//        else {
//            return modelMapper.map(service.addNewAnnouncement(updateDTO), UpdateDTO.class);
//        }
//    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public UpdateDTO addNewAnnouncement(@RequestBody UpdateDTO updateDTO) {
        return service.addNewAnnouncement(updateDTO);
    }






//    @PutMapping("/{id}")
//    public UpdateDTO updateAnnouncement(@PathVariable int id, @RequestBody UpdateDTO updateAnnouncement) {
//        if(updateAnnouncement.getPublishDate() != null && updateAnnouncement.getCloseDate() == null ){
//            if(updateAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())) {
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
//                throw new ValidationException(fieldErrors);
//            }
//            return modelMapper.map(service.updateAnnouncement(id,updateAnnouncement),UpdateDTO.class);
//        }
//        else if(updateAnnouncement.getPublishDate() == null && updateAnnouncement.getCloseDate() != null ){
//            if(updateAnnouncement.getCloseDate().isBefore(ZonedDateTime.now())) {
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("closeDate", "must be a future date"));
//                throw new ValidationException(fieldErrors);
//            }
//            return modelMapper.map(service.updateAnnouncement(id,updateAnnouncement),UpdateDTO.class);
//        }
//        else if(updateAnnouncement.getPublishDate() != null && updateAnnouncement.getCloseDate() != null){
//            if (updateAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())){
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
//                throw new ValidationException(fieldErrors);
//            }
//            else if (updateAnnouncement.getCloseDate().isBefore(updateAnnouncement.getPublishDate())) {
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
//                throw new ValidationException(fieldErrors);
//            }
//            else if (updateAnnouncement.getCloseDate().equals(updateAnnouncement.getPublishDate())){
//                List<FieldErrorDto> fieldErrors = new ArrayList<>();
//                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
//                throw new ValidationException(fieldErrors);
//            }
//            return modelMapper.map(service.updateAnnouncement(id,updateAnnouncement),UpdateDTO.class);
//
//        }
//        else if(updateAnnouncement.getAnnouncementDescription().length() <1 || updateAnnouncement.getAnnouncementDescription().length() >10000){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else if(updateAnnouncement.getAnnouncementTitle().trim().length() <1 || updateAnnouncement.getAnnouncementTitle().trim().length() >200){
//            List<FieldErrorDto> fieldErrors = new ArrayList<>();
//            fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
//            throw new ValidationException(fieldErrors);
//        }
//        else {     return modelMapper.map(service.updateAnnouncement(id,updateAnnouncement),UpdateDTO.class);}
//
//    }
    @PutMapping("/{id}")
    public UpdateDTO updateAnnouncement(@PathVariable int id, @RequestBody UpdateDTO updateAnnouncement) {
            return modelMapper.map(service.updateAnnouncement(id,updateAnnouncement),UpdateDTO.class);
    }









    @DeleteMapping("/{id}")
    public void deleteAnnouncement(@PathVariable int id) {
        service.deleteAnnouncement(id);
    }


    @GetMapping("")
    public List<AnnouncementUserDTO> getAnnouncementByModeAndCategory(@RequestParam(defaultValue = "admin") String mode, @RequestParam(required = false) Integer category) {
        List<Announcement> announcementList = service.getAnnouncementByModeAndCategory(mode,category);
        List<AnnouncementUserDTO> announcementDTOList =
                announcementList.stream().map(c -> modelMapper.map(c, AnnouncementUserDTO.class)).collect(Collectors.toList());
        return announcementDTOList;

    }


    @GetMapping("/pages")
    public PageDTO<AnnouncementUserDTO> getAnnouncementByModeWithPagination(@RequestParam(defaultValue = "admin") String mode, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, @RequestParam(required = false) Integer category) {
        PageDTO<Announcement> announcementPage = service.getAnnouncementByModeWithPagination(mode,page,size,category);
        List<AnnouncementUserDTO> announcementDTOList = announcementPage.getContent().stream().map(c -> modelMapper.map(c, AnnouncementUserDTO.class)).collect(Collectors.toList());
        PageDTO<AnnouncementUserDTO> announcementUserDTOPage = modelMapper.map(announcementPage,PageDTO.class);
        announcementUserDTOPage.setContent(announcementDTOList);
        return announcementUserDTOPage;
    }

}
