package ssi2.moontiw_announcement.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ssi2.moontiw_announcement.dtos.AnnouncementUserDTO;
import ssi2.moontiw_announcement.dtos.PageDTO;
import ssi2.moontiw_announcement.dtos.UpdateDTO;
import ssi2.moontiw_announcement.entities.Announcement;
import ssi2.moontiw_announcement.entities.Categories;
import ssi2.moontiw_announcement.exception.ValidationException;
import ssi2.moontiw_announcement.repositories.AnnouncementRepository;
import ssi2.moontiw_announcement.repositories.CategoryRepository;
import ssi2.moontiw_announcement.utils.ListMapper;
import ssi2.moontiw_announcement.exception.FieldErrorDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Announcement> getAllAnnouncement() {return announcementRepository.findAllByOrderByIdDesc();}

    public Announcement getAnnouncementById(int id) {
        return announcementRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Announcement id "+ id + "does not exist"));}









//    public Announcement updateAnnouncement(int id, UpdateDTO newAnnouncement) {
//        UpdateDTO announcement = modelMapper.map(getAnnouncementById(id),UpdateDTO.class);
//        announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
//        announcement.setAnnouncementCategoryId(newAnnouncement.getAnnouncementCategoryId());
//        announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
//        announcement.setPublishDate(newAnnouncement.getPublishDate());
//        announcement.setCloseDate(newAnnouncement.getCloseDate());
//        announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
//        return announcementRepository.saveAndFlush(modelMapper.map(announcement,Announcement.class));
//    }
    public Announcement updateAnnouncement(int id, UpdateDTO newAnnouncement) {
        UpdateDTO announcement = modelMapper.map(getAnnouncementById(id),UpdateDTO.class);
        if (newAnnouncement.getPublishDate() != null && newAnnouncement.getCloseDate() == null){
            if(newAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
                throw new ValidationException(fieldErrors);
            }
            announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
            announcement.setAnnouncementCategoryId(newAnnouncement.getAnnouncementCategoryId());
            announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
            announcement.setPublishDate(newAnnouncement.getPublishDate());
            announcement.setCloseDate(newAnnouncement.getCloseDate());
            announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
            return announcementRepository.saveAndFlush(modelMapper.map(announcement,Announcement.class));

        }
        else if(newAnnouncement.getPublishDate() == null && newAnnouncement.getCloseDate() != null ){
            if(newAnnouncement.getCloseDate().isBefore(ZonedDateTime.now())) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("closeDate", "must be a future date"));
                throw new ValidationException(fieldErrors);
            }
            announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
            announcement.setAnnouncementCategoryId(newAnnouncement.getAnnouncementCategoryId());
            announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
            announcement.setPublishDate(newAnnouncement.getPublishDate());
            announcement.setCloseDate(newAnnouncement.getCloseDate());
            announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
            return announcementRepository.saveAndFlush(modelMapper.map(announcement,Announcement.class));
        }
        else if(newAnnouncement.getPublishDate() != null && newAnnouncement.getCloseDate() != null){
            if (newAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
                throw new ValidationException(fieldErrors);
            }
            else if (newAnnouncement.getCloseDate().isBefore(newAnnouncement.getPublishDate())) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
                throw new ValidationException(fieldErrors);
            }
            else if (newAnnouncement.getCloseDate().equals(newAnnouncement.getPublishDate())){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
                throw new ValidationException(fieldErrors);
            }
            announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
            announcement.setAnnouncementCategoryId(newAnnouncement.getAnnouncementCategoryId());
            announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
            announcement.setPublishDate(newAnnouncement.getPublishDate());
            announcement.setCloseDate(newAnnouncement.getCloseDate());
            announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
            return announcementRepository.saveAndFlush(modelMapper.map(announcement,Announcement.class));

        }else if(newAnnouncement.getAnnouncementDescription().length() <1 || newAnnouncement.getAnnouncementDescription().length() >10000){
            List<FieldErrorDto> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
            throw new ValidationException(fieldErrors);
        }
        else if(newAnnouncement.getAnnouncementTitle().trim().length() <1 || newAnnouncement.getAnnouncementTitle().trim().length() >200){
            List<FieldErrorDto> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
            throw new ValidationException(fieldErrors);
        }
        else {     announcement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
            announcement.setAnnouncementCategoryId(newAnnouncement.getAnnouncementCategoryId());
            announcement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
            announcement.setPublishDate(newAnnouncement.getPublishDate());
            announcement.setCloseDate(newAnnouncement.getCloseDate());
            announcement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
            return announcementRepository.saveAndFlush(modelMapper.map(announcement,Announcement.class));}
    }








//    public UpdateDTO addNewAnnouncement(UpdateDTO newAnnouncement) {
//        Integer CategoryId = newAnnouncement.getAnnouncementCategoryId();
//        Categories category = categoryRepository.findById(CategoryId).orElseThrow(()->new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "Category id "+ CategoryId + "does not exist"));
//        Announcement NewAnnouncement= new Announcement();
//        NewAnnouncement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
//        NewAnnouncement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
//        NewAnnouncement.setCategory(category);
//        NewAnnouncement.setPublishDate(newAnnouncement.getPublishDate());
//        NewAnnouncement.setCloseDate(newAnnouncement.getCloseDate());
//        NewAnnouncement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
//        return modelMapper.map(announcementRepository.saveAndFlush(NewAnnouncement),UpdateDTO.class);
//    }
        public UpdateDTO addNewAnnouncement(UpdateDTO newAnnouncement) {
            Integer CategoryId = newAnnouncement.getAnnouncementCategoryId();
            Categories category = categoryRepository.findById(CategoryId).orElseThrow(()->new ResponseStatusException(
                   HttpStatus.NOT_FOUND, "Category id "+ CategoryId + "does not exist"));
            Announcement NewAnnouncement= new Announcement();
            if (newAnnouncement.getAnnouncementTitle() == null) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be null"));
                throw new ValidationException(fieldErrors);
            }
            else if (newAnnouncement.getAnnouncementTitle().length() <1 || newAnnouncement.getAnnouncementTitle().length() >200){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementDescription() == null){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be null"));
                throw new ValidationException(fieldErrors);
            }
            else if (newAnnouncement.getAnnouncementDescription().length() <1 || newAnnouncement.getAnnouncementDescription().length() >10000){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementTitle().trim().length() <1 || newAnnouncement.getAnnouncementTitle().trim().length() >200){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementTitle", "must not be blank"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementDisplay() == null ) {
                NewAnnouncement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay()==null?"N": newAnnouncement.getAnnouncementDisplay());
                NewAnnouncement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
                NewAnnouncement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
                NewAnnouncement.setCategory(category);
                NewAnnouncement.setPublishDate(newAnnouncement.getPublishDate());
                NewAnnouncement.setCloseDate(newAnnouncement.getCloseDate());
                NewAnnouncement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
                return  modelMapper.map(announcementRepository.saveAndFlush(modelMapper.map(newAnnouncement,Announcement.class)),UpdateDTO.class);
            }
            else if(newAnnouncement.getAnnouncementDescription().trim() == "" ){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementDescription", "must not be blank"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementCategoryId() == null){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("categoryId", "must not be null"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementCategoryId() != 1 && newAnnouncement.getAnnouncementCategoryId() != 2 && newAnnouncement.getAnnouncementCategoryId() != 3 && newAnnouncement.getAnnouncementCategoryId() != 4){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("categoryId", "does not exists"));
                throw new ValidationException(fieldErrors);
            }
            else if(!newAnnouncement.getAnnouncementDisplay().equals("Y") && !newAnnouncement.getAnnouncementDisplay().equals("N")){
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementDisplay", "must be either 'Y' or 'N'"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getAnnouncementDisplay().trim() == "" ) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("announcementDisplay", "must be either 'Y' or 'N'"));
                throw new ValidationException(fieldErrors);
            }
            else if(newAnnouncement.getPublishDate()!=null && newAnnouncement.getCloseDate()!=null){
                if (newAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())){
                    List<FieldErrorDto> fieldErrors = new ArrayList<>();
                    fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
                    throw new ValidationException(fieldErrors);
                }
                if (newAnnouncement.getCloseDate().equals(newAnnouncement.getPublishDate())) {
                    List<FieldErrorDto> fieldErrors = new ArrayList<>();
                    fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
                    throw new ValidationException(fieldErrors);
                }
                if (newAnnouncement.getCloseDate().isBefore(newAnnouncement.getPublishDate())) {
                    List<FieldErrorDto> fieldErrors = new ArrayList<>();
                    fieldErrors.add(new FieldErrorDto("closeDate", "must be later than publish date"));
                    throw new ValidationException(fieldErrors);
                }else{
                    NewAnnouncement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
                    NewAnnouncement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
                    NewAnnouncement.setCategory(category);
                    NewAnnouncement.setPublishDate(newAnnouncement.getPublishDate());
                    NewAnnouncement.setCloseDate(newAnnouncement.getCloseDate());
                    NewAnnouncement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
                   return modelMapper.map(announcementRepository.saveAndFlush(NewAnnouncement),UpdateDTO.class);

                }
            }
            else if (newAnnouncement.getPublishDate()!=null && newAnnouncement.getCloseDate()==null && newAnnouncement.getPublishDate().isBefore(ZonedDateTime.now())) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("publishDate", "must be a date in the present or in the future"));
                throw new ValidationException(fieldErrors);
            }
            else if (newAnnouncement.getPublishDate()==null && newAnnouncement.getCloseDate()!=null && newAnnouncement.getCloseDate().isBefore(ZonedDateTime.now())) {
                List<FieldErrorDto> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldErrorDto("closeDate", "must be a future date"));
                throw new ValidationException(fieldErrors);
            }
            else {
                NewAnnouncement.setAnnouncementTitle(newAnnouncement.getAnnouncementTitle());
                NewAnnouncement.setAnnouncementDescription(newAnnouncement.getAnnouncementDescription());
                NewAnnouncement.setCategory(category);
                NewAnnouncement.setPublishDate(newAnnouncement.getPublishDate());
                NewAnnouncement.setCloseDate(newAnnouncement.getCloseDate());
                NewAnnouncement.setAnnouncementDisplay(newAnnouncement.getAnnouncementDisplay());
                return modelMapper.map(announcementRepository.saveAndFlush(NewAnnouncement),UpdateDTO.class);

            }
        }


    public void deleteAnnouncement(int id) {
        Announcement announcement = getAnnouncementById(id);
        announcementRepository.delete(announcement);
    }

    
    public List<Announcement> getAnnouncementByMode(String mode) {
    ZonedDateTime now = ZonedDateTime.now();
    return announcementRepository.findAllByOrderByIdDesc().stream().filter(
                announcement -> {
                    if(mode.equals("active")){
                        if (announcement.getPublishDate()==null && announcement.getCloseDate()==null){
                            return announcement.getAnnouncementDisplay().equals("Y");
                        }
                        if(announcement.getPublishDate()!=null && announcement.getCloseDate()==null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getPublishDate().isBefore(now);
                        }
                        if (announcement.getPublishDate()!=null && announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getPublishDate().isBefore(now) && announcement.getCloseDate().isAfter(now);
                        }
                        if (announcement.getPublishDate()==null && announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getCloseDate().isAfter(now);
                        }
                        return false;
                    }
                    else if(mode.equals("close")){
                        if (announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getCloseDate().isBefore(now);
                        }
                        return false;
                    }
                    else if(mode.equals("admin")){
                        return true;
                    }
                    else{
                        throw new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Mode "+ mode + "does not exist");
                    }
                }
        ).collect(Collectors.toList());



    }
    public PageDTO<Announcement> getAnnouncementByModeWithPagination(String mode, int page, int size ,Integer categoryId) {
        List<Announcement> announcement = getAnnouncementByMode(mode).stream().filter(
                announcements -> {
                    if (categoryId == null || categoryId == 0) {
                        return true;
                    }
                    return announcements.getCategory().getCategoryId().equals(categoryId);
                }
        ).collect(Collectors.toList());
        Page page1 = listMapper.convertToPage(announcement, PageRequest.of(page, size));
        return listMapper.toPageDTO(page1, Announcement.class, modelMapper);
    }



    public  List<Announcement> getAnnouncementByModeAndCategory(String mode, Integer categoryId) {
        ZonedDateTime now = ZonedDateTime.now();
	if  (categoryId == null||categoryId == 0) {
            return getAnnouncementByMode(mode).stream().collect(Collectors.toList());
        }else{
        return getAnnouncementByMode(mode).stream().filter(
                announcement -> {
                    if(mode.equals("active")){
                        if (announcement.getPublishDate()==null && announcement.getCloseDate()==null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getCategory().getCategoryId().equals(categoryId);
                        }
                        if(announcement.getPublishDate()!=null && announcement.getCloseDate()==null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getPublishDate().isBefore(now) && announcement.getCategory().getCategoryId().equals(categoryId);
                        }
                        if (announcement.getPublishDate()!=null && announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getPublishDate().isBefore(now) && announcement.getCloseDate().isAfter(now) && announcement.getCategory().getCategoryId().equals(categoryId);
                        }
                        if (announcement.getPublishDate()==null && announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getCloseDate().isAfter(now) && announcement.getCategory().getCategoryId().equals(categoryId);
                        }
                        return false;
                    }
                    else if(mode.equals("close")){
                        if (announcement.getCloseDate()!=null){
                            return announcement.getAnnouncementDisplay().equals("Y") && announcement.getCloseDate().isBefore(now) && announcement.getCategory().getCategoryId().equals(categoryId);
                        }
                        return false;
                    }
                    else if(mode.equals("admin")){
                        return true;
                    }
                    else{
                        throw new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Mode "+ mode + "does not exist");
                    }
                }

        ).collect(Collectors.toList());}
    }






}
