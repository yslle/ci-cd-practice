package umc.cicd.service.StoreService;

import umc.cicd.domain.Mission;
import umc.cicd.domain.Review;
import umc.cicd.domain.Store;
import umc.cicd.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.JoinDto request);

    Review createReview(StoreRequestDTO.CreateReviewDto request, Long storeId, Long memberId);

    Mission createMission(StoreRequestDTO.CreateMissionDto request, Long storeId);

    Long deleteReview(Long reviewId);
}
