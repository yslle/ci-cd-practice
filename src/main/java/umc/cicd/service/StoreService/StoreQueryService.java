package umc.cicd.service.StoreService;

import org.springframework.data.domain.Page;
import umc.cicd.domain.Mission;
import umc.cicd.domain.Review;
import umc.cicd.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Optional<Mission> findMission(Long id);

    Page<Review> getReviewList(Long StoreId, Integer page);

    Page<Mission> getMissionList(Long StoreId, Integer page);
}
