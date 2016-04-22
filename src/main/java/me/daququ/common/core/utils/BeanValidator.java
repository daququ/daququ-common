package me.daququ.common.core.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public class BeanValidator   {

    private static Validator validator;
    static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    
    static{
    	validator = validatorFactory.usingContext().getValidator();
    }

    public List<Map<String,String>> validate(Object target) {
    	List<Map<String, String>> l = Lists.newArrayList();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
//            errors.rejectValue(propertyPath, "", message);
            l.add(ImmutableMap.of("feild",propertyPath,"message",message));
        }
        return l;
    }
}