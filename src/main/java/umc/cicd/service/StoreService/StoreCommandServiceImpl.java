package umc.cicd.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.cicd.apiPayload.code.status.ErrorStatus;
import umc.cicd.apiPayload.exception.handler.RegionHandler;
import umc.cicd.apiPayload.exception.handler.ReviewHandler;
import umc.cicd.apiPayload.exception.handler.StoreHandler;
import umc.cicd.converter.StoreConverter;
import umc.cicd.domain.*;
import umc.cicd.repository.*;
import umc.cicd.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final UuidRepository uuidRepository;
//    private final AmazonS3Manager s3Manager;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Store joinStore(StoreRequestDTO.JoinDto request) {

        // region 조회
        Region region = regionRepository.findByName(request.getRegion())
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        // store 저장
        Store newStore = StoreConverter.toStore(request);
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

    @Override
    public Review createReview(StoreRequestDTO.CreateReviewDto request, Long storeId, Long memberId) {
        Review review = StoreConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

//        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), request.getReviewPicture());

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());


//        reviewImageRepository.save(StoreConverter.toReviewImage(pictureUrl,review));
        return reviewRepository.save(review);
    }

    @Override
    public Long deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewHandler(ErrorStatus.REVIEW_NOT_FOUND));

        // S3에서 파일 삭제
//        List<ReviewImage> reviewImages = review.getReviewImageList();
//        for (ReviewImage reviewImage : reviewImages) {
//            s3Manager.deleteFile(reviewImage.getImageUrl());
//        }
//
//        reviewImageRepository.deleteAll(reviewImages);
        reviewRepository.deleteById(reviewId);

        return reviewId;
    }

    @Override
    public Mission createMission(StoreRequestDTO.CreateMissionDto request, Long storeId) {
        // store 조회
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission newMission = StoreConverter.toMission(request);
        newMission.setStore(store);

        return missionRepository.save(newMission);
    }

}
