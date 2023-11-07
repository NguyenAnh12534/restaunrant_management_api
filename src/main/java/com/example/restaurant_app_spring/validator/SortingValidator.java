package com.example.restaurant_app_spring.validator;

import com.example.restaurant_app_spring.common.annotation.validator.SortingConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class SortingValidator implements ConstraintValidator<SortingConstraint, String> {
    private SortingConstraint sortingConstraint;

    @Override
    public void initialize(SortingConstraint constraintAnnotation) {
        this.sortingConstraint = constraintAnnotation;
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return true;

        Class<?> sortingClass = sortingConstraint.sortClass();
        String[] requestedFields = value.split(",");
        for(String sortField : requestedFields) {
           boolean isValid = this.checkFieldInClass(sortField, sortingClass);
           if(!isValid) {
               return false;
           }
        }

        return true;
    }

    private boolean checkFieldInClass(String fieldName, Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        boolean isValidField = false;
        for(Field classField : fields) {
            if(fieldName.equals(classField.getName()) || fieldName.equals("-" + classField.getName()))
                isValidField = true;
        }
        if(!isValidField)
            return false;

        return true;
    }

}