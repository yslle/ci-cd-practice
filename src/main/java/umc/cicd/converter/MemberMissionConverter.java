package umc.cicd.converter;

import org.springframework.data.domain.Page;
import umc.cicd.domain.mapping.MemberMission;
import umc.cicd.web.dto.MemberResponseDTO;
import umc.cicd.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberResponseDTO.MemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MemberResponseDTO.MemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewDTO toMissionPreViewDTO(MemberMission memberMission) {
        return StoreResponseDTO.MissionPreViewDTO.builder()
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .content(memberMission.getMission().getContent())
                .build();
    }

    public static StoreResponseDTO.MissionPreViewListDTO toMissionPreViewListDTO(Page<MemberMission> memberMissionList) {
        List<StoreResponseDTO.MissionPreViewDTO> missionPreViewDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::toMissionPreViewDTO).collect(Collectors.toList());
        return StoreResponseDTO.MissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
