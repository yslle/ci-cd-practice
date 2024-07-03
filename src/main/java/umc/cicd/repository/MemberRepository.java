package umc.cicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.cicd.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String name);
}
