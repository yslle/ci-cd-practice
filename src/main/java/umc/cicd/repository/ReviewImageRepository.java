package umc.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.cicd.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
