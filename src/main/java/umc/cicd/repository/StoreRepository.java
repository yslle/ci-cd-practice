package umc.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.cicd.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
