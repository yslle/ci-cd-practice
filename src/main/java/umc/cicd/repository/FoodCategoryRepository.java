package umc.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.cicd.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
