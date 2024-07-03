package umc.cicd.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.cicd.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented     // 사용자 정의 어노테이션
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}