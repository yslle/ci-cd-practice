package umc.cicd.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.cicd.validation.annotation.ExistCategories;
import umc.cicd.validation.annotation.ExistMember;
import umc.cicd.validation.annotation.ExistMission;
import umc.cicd.validation.annotation.IsChallengingMission;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        String address;
        @NotNull
        String phone;
        @NotNull
        Integer gender;
        @ExistCategories
        List<Long> preferCategory;
    }

    @Getter
    @IsChallengingMission
    public static class DoMission {
        @ExistMember
        Long memberId;
        @ExistMission
        Long missionId;
    }

}