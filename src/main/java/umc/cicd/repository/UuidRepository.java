package umc.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.cicd.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
