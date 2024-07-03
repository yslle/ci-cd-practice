package umc.cicd.service.MemberService;

import org.springframework.transaction.annotation.Transactional;
import umc.cicd.domain.Member;
import umc.cicd.domain.mapping.MemberMission;
import umc.cicd.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);

    MemberMission createMemberMission(Long memberId, Long missionId);
}
