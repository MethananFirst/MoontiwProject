package ssi2.moontiw_announcement.validated;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ssi2.moontiw_announcement.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;


public class UniqueUpdateUser implements ConstraintValidator<ValidateUpdateUniqueCheck, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ValidateUpdateUniqueCheck containAnnotation){
    }


    @Override
    public boolean isValid(String data, ConstraintValidatorContext constraintValidatorContext){

    String url = request.getRequestURL().toString();
    String id = url.substring(url.lastIndexOf('/') + 1);

    if(repository.findUserByUsernameAndIdNot(data, id)==null && repository.findUserByNameAndIdNot(data, id)==null && repository.findUserByEmailAndIdNot(data, id)==null){
        return true;
    } else {
        return false;
        }
    }
}

