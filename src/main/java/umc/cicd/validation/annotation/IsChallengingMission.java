package umc.cicd.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.cicd.validation.validator.MissionChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengingValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsChallengingMission {
    String message() default "해당 미션에 이미 도전중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}