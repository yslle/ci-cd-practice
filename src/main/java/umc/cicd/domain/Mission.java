package umc.cicd.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.cicd.domain.common.BaseEntity;
import umc.cicd.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, length = 20)
    private String ownerCode;

    private LocalDate deadline;

    private Integer reward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    // 연관 관계 편의 메서드
    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getMissionList().remove(this);
        }
        this.store = store;
        if (store != null) {
            store.getMissionList().add(this);
        }
    }

}