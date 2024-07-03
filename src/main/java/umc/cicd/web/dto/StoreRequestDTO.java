package umc.cicd.web.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class JoinDto {
        @NotEmpty
        String name;
        @NotNull
        String category;
        @NotNull
        String address;
        @NotNull
        String operatingHours;
        @NotNull
        String region;
    }

    @Getter @Setter
    public static class CreateReviewDto {
        @NotEmpty
        String content;
        @NotNull
        Float rating;

        MultipartFile reviewPicture;
    }

    @Getter
    public static class CreateMissionDto {
        @NotEmpty
        String content;
        @NotNull
        String ownerCode;
        @NotNull
        LocalDate deadline;
        @NotNull
        Integer reward;
    }

}
