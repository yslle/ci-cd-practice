package umc.cicd.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.cicd.apiPayload.code.status.ErrorStatus;
import umc.cicd.apiPayload.exception.ExceptionAdvice;
import umc.cicd.apiPayload.exception.GeneralException;
import umc.cicd.apiPayload.exception.handler.FoodCategoryHandler;
import umc.cicd.apiPayload.exception.handler.StoreHandler;
import umc.cicd.converter.MemberConverter;
import umc.cicd.converter.MemberPreferConverter;
import umc.cicd.domain.FoodCategory;
import umc.cicd.domain.Member;
import umc.cicd.domain.Mission;
import umc.cicd.domain.Store;
import umc.cicd.domain.enums.MissionStatus;
import umc.cicd.domain.mapping.MemberMission;
import umc.cicd.domain.mapping.MemberPrefer;
import umc.cicd.repository.FoodCategoryRepository;
import umc.cicd.repository.MemberMissionRepository;
import umc.cicd.repository.MemberRepository;
import umc.cicd.repository.MissionRepository;
import umc.cicd.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        // converter에서 member 엔티티 생성
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // converter에서 memberPrefer 엔티티 생성
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        // 양방향 연관 관계 설정
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public MemberMission createMemberMission(Long memberId, Long missionId) {
        Optional<Member> member = memberRepository.findById(memberId);
//                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Optional<Mission> mission = missionRepository.findById(missionId);
//                .orElseThrow(() -> new StoreHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
        memberMission.setMember(member.get());
        memberMission.setMission(mission.get());
        return memberMissionRepository.save(memberMission);
    }

}
