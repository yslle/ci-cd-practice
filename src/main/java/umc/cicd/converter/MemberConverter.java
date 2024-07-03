package umc.cicd.converter;

import umc.cicd.domain.Member;
import umc.cicd.domain.enums.Gender;
import umc.cicd.domain.enums.MemberRole;
import umc.cicd.web.dto.MemberRequestDTO;
import umc.cicd.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {
        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .gender(gender)
                .role(MemberRole.USER)
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
