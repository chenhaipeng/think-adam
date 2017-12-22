package com.thinkme.common.utils;


import com.thinkme.utils.base.BeanUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2016/11/26 17:35
 */
public class ValidatorUtil {
    public static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 使用jsr303 校验
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> void validator(T t, Class<?>... groups) {
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t, groups);
        if (BeanUtils.isNotEmpty(violations)) {
            throw new IllegalArgumentException(violations.iterator().next().getMessage());
        }
    }
}
