package teamD.publicParking.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import teamD.publicParking.board.Entity.BoardEntity;


public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	//Board findByNickname(String nickname);
	List<BoardEntity> findByTitleContaining(String keyword);
	
}