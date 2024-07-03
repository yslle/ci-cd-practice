package umc.cicd.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.cicd.domain.Member;
import umc.cicd.domain.Mission;
import umc.cicd.domain.common.BaseEntity;
import umc.cicd.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    // 연관 관계 편의 메서드
    public void setMember(Member member){
        if(this.member != null)
            member.getMemberMissionList().remove(this);
        this.member = member;
        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission) {
        if (this.mission != null) {
            this.mission.getMemberMissionList().remove(this);
        }
        this.mission = mission;
        if (mission != null) {
            mission.getMemberMissionList().add(this);
        }
    }

}