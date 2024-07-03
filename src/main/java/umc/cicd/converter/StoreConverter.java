package umc.cicd.converter;

import org.springframework.data.domain.Page;
import umc.cicd.domain.Mission;
import umc.cicd.domain.Review;
import umc.cicd.domain.ReviewImage;
import umc.cicd.domain.Store;
import umc.cicd.web.dto.StoreRequestDTO;
import umc.cicd.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    // store
    public static StoreResponseDTO.JoinResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.JoinResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.JoinDto request) {
        return Store.builder()
                .name(request.getName())
                .category(request.getCategory())
                .address(request.getAddress())
                .operatingHours(request.getOperatingHours())
                .missionList(new ArrayList<>())
                .build();
    }

    // review
    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.DeleteReviewResultDTO toDeleteReviewResultDTO(Long reviewId) {
        return StoreResponseDTO.DeleteReviewResultDTO.builder()
                .reviewId(reviewId)
                .build();
    }

    public static Review toReview(StoreRequestDTO.CreateReviewDto request){
        return Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .reviewImageList(new ArrayList<>())
                .build();
    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .imageUrl(pictureUrl)
                .review(review)
                .build();
    }

    // mission
    public static StoreResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return StoreResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.CreateMissionDto request){
        return Mission.builder()
                .content(request.getContent())
                .ownerCode(request.getOwnerCode())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .memberMissionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}
