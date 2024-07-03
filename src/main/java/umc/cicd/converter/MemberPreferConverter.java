package umc.cicd.converter;

import umc.cicd.domain.FoodCategory;
import umc.cicd.domain.mapping.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    // FoodCategory 리스트로 MemberPrefer 엔티티 생성
    // 양방향 연관 관계 설정은 service에서 하는 게 좋음(여기서 member를 넣지 x)
    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
