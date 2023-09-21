package ssi2.moontiw_announcement.validated;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ssi2.moontiw_announcement.repositories.UserRepository;

public class UniqueUserDetail implements ConstraintValidator<ValidateUniqueCheck, String> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidateUniqueCheck containAnnotation){

    }

    @Override
    public boolean isValid(String data, ConstraintValidatorContext constraintValidatorContext){
        if(repository.findUserByUsername(data)==null&&repository.findUserByName(data)==null&&repository.findUserByEmail(data)==null){
            return true;
        }
        else{
            return false;
        }

    }
}
