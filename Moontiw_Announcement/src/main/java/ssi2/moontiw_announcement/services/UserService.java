package ssi2.moontiw_announcement.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ssi2.moontiw_announcement.dtos.AddUserDTO;
import ssi2.moontiw_announcement.dtos.UpdateUserDTO;
import ssi2.moontiw_announcement.dtos.UserDTO;
import ssi2.moontiw_announcement.dtos.UserMatchDTO;
import ssi2.moontiw_announcement.entities.Users;
import ssi2.moontiw_announcement.repositories.UserRepository;
import ssi2.moontiw_announcement.utils.ListMapper;


import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUser() {
        Sort sortByRole = Sort.by(Sort.Direction.ASC, "role");
        Sort sortByUsername = Sort.by(Sort.Direction.ASC, "username");
        return userRepository.findAll(sortByRole.and(sortByUsername));
    }

    public Users getUserById(Integer userId) {return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User id "+ userId + "does not exist"));}

    public Users getPasswordByUsername(String username) {return userRepository.findPasswordByUsername(username);}


    public UserDTO updateUser(int userId, UpdateUserDTO newUpdateUser) {
        Users user = modelMapper.map(getUserById(userId), Users.class);
        if (!(user.getUsername().equals(newUpdateUser.getUsername().trim())
                && user.getName().equals(newUpdateUser.getName().trim())
                && user.getEmail().equals(newUpdateUser.getEmail().trim())
                && user.getRole().equals(newUpdateUser.getRole().trim()))) {
            user.setUsername(newUpdateUser.getUsername().trim());
            user.setName(newUpdateUser.getName().trim());
            user.setEmail(newUpdateUser.getEmail().trim());
            user.setRole(newUpdateUser.getRole().trim());
        }
        Users result = userRepository.saveAndFlush(user);
        userRepository.refresh(result);
        return modelMapper.map(result,UserDTO.class);
    }
//public Users updateUser(int userId, Users newUpdateUser) {
//    Users user = modelMapper.map(getUserById(userId), Users.class);
//    if(newUpdateUser.getUsername()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getUsername().trim().length() == 0){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getUsername().trim().length() > 45){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "size must be between 1 and 45"));
//        throw new ValidationException(fieldErrors);
//    } else if(newUpdateUser.getName()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getName().trim().length()==0 ){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getName().trim().length()>100 ){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "size must be between 1 and 100"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getEmail()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getEmail().trim().length()==0){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(newUpdateUser.getEmail().trim().length()>150){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "size must be between 1 and 150"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByUsername(newUpdateUser.getUsername()) != null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByName(newUpdateUser.getName()) != null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByEmail(newUpdateUser.getEmail()) != null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    } else {
//        if (!(user.getUsername().equals(newUpdateUser.getUsername().trim())
//            && user.getPassword().equals(newUpdateUser.getPassword().trim())
//            && user.getName().equals(newUpdateUser.getName().trim())
//            && user.getEmail().equals(newUpdateUser.getEmail().trim())
//            && user.getRole().equals(newUpdateUser.getRole().trim()))) {
//        user.setUsername(newUpdateUser.getUsername().trim());
//        user.setPassword(newUpdateUser.getPassword().trim());
//        user.setName(newUpdateUser.getName().trim());
//        user.setEmail(newUpdateUser.getEmail().trim());
//        user.setRole(newUpdateUser.getRole().trim());
//        Users result = userRepository.saveAndFlush(user);
//        userRepository.refresh(result);
//        return result;}
//    }
//    Users result = userRepository.saveAndFlush(user);
//    userRepository.refresh(result);
//    return result;
//}




        public UserDTO addNewUser(AddUserDTO NewUser) {
        Users user = new Users();
            user.setUsername(NewUser.getUsername().trim());
            Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 1);
            String springBouncyHash = argon2PasswordEncoder.encode(NewUser.getPassword().trim());
            user.setPassword(springBouncyHash);
            user.setName(NewUser.getName().trim());
            user.setEmail(NewUser.getEmail().trim());
            user.setRole(NewUser.getRole().trim());
            Users result = userRepository.saveAndFlush(user);
            userRepository.refresh(result);
            return modelMapper.map(result,UserDTO.class);
    }
//public Users addNewUser(Users NewUser) {
//
//    Users user = new Users();
//    if(NewUser.getUsername()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getUsername().trim().length() == 0){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getUsername().trim().length() > 45){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "size must be between 1 and 45"));
//        throw new ValidationException(fieldErrors);
//    } else if(NewUser.getPassword()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("password", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getPassword().trim().length() == 0){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("password", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getPassword().trim().length()>100){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("password", "size must be between 1 and 100"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getName()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getName().trim().length()==0 ){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getName().trim().length()>100 ){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "size must be between 1 and 100"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getEmail()==null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getEmail().trim().length()==0){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "must not be blank"));
//        throw new ValidationException(fieldErrors);
//    }else if(NewUser.getEmail().trim().length()>150){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "size must be between 1 and 150"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByUsername(NewUser.getUsername()) != null ){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("username", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByName(NewUser.getName()) != null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("name", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    }else if(userRepository.findByEmail(NewUser.getEmail()) != null){
//        List<FieldErrorDto> fieldErrors = new ArrayList<>();
//        fieldErrors.add(new FieldErrorDto("email", "does not unique"));
//        throw new ValidationException(fieldErrors);
//    } else{
//        user.setUsername(NewUser.getUsername().trim());
//        user.setPassword(NewUser.getPassword().trim());
//        user.setName(NewUser.getName().trim());
//        user.setEmail(NewUser.getEmail().trim());
//        user.setRole(NewUser.getRole().trim());
//        Users result = userRepository.saveAndFlush(user);
//        userRepository.refresh(result);
//        return result;
//    }
//}

//    public UserMatchDTO matchPasswordUser (UserMatchDTO user){
//        String userPassword = userRepository.findPasswordByUsername(user.getUsername());
//        if (userPassword == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"username does not exist");
//        }
//        Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 1);
//        if(argon2PasswordEncoder.matches(user.getPassword(),userPassword)){
//            throw new ResponseStatusException(HttpStatus.OK,"correct password");
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"incorrect password");
//        }
//
//    }

    public UserMatchDTO matchPasswordUser(UserMatchDTO user) {
        Users userEntity = userRepository.findByUsername(user.getUsername());
        if (userEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username does not exist");
        }
            String userPassword = userEntity.getPassword();
            Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 60000, 1);
            if (argon2PasswordEncoder.matches(user.getPassword(), userPassword)) {
            throw new ResponseStatusException(HttpStatus.OK, "Correct password");
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
    }






    public void deleteUser(int userId) {
        Users user = getUserById(userId);
        userRepository.delete(user);
    }










}
