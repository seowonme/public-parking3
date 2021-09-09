package teamD.publicParking.user.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teamD.publicParking.user.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Optional<Member> findByEmail(String userEmail);	
	
}
