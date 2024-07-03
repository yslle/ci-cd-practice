package umc.cicd.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.cicd.domain.enums.MissionStatus;
import umc.cicd.domain.mapping.MemberMission;
import umc.cicd.service.MemberService.MemberQueryService;
import umc.cicd.validation.annotation.IsChallengingMission;
import umc.cicd.web.dto.MemberRequestDTO;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<IsChallengingMission, MemberRequestDTO.DoMission> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(IsChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }


    @Override
    public boolean isValid(MemberRequestDTO.DoMission value, ConstraintValidatorContext context) {
        Optional<MemberMission> target = memberQueryService.findMemberMissionByMemberAndMission(value.getMemberId(), value.getMissionId());

        if (target.isPresent() && target.get().getStatus() == MissionStatus.CHALLENGING){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
            return false;
        }
        return true;
    }
}