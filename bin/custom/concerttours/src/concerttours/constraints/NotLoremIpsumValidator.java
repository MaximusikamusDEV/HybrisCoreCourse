package concerttours.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotLoremIpsumValidator implements ConstraintValidator<NotLoremIpsum, String> {
    @Override
    public void initialize(NotLoremIpsum constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.trim().isEmpty()){
            return true;
        }

        return !s.toLowerCase().startsWith("lorem ipsum");
    }
}
