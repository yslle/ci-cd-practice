package umc.cicd.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.cicd.domain.Mission;
import umc.cicd.domain.Review;
import umc.cicd.domain.Store;
import umc.cicd.repository.MissionRepository;
import umc.cicd.repository.ReviewRepository;
import umc.cicd.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Mission> getMissionList(Long StoreId, Integer page) {
        Store store = storeRepository.findById(StoreId).get();
        Page<Mission> StorePage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

}