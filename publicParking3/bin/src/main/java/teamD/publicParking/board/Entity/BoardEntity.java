package teamD.publicParking.board.Entity;

import java.io.Serializable;

import javax.persistence.*;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamD.publicParking.board.Entity.enums.BoardType;

@Table(name="board")
@Getter
@Entity //db 테이블과 매핑되는 객체
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@EntityListeners(AuditingEntityListener.class)
public class BoardEntity extends TimeEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long board_id;
	
	@Column(length = 20)
	private String nickname;
	
	@Column(length = 50, nullable = false)
	private String title;
	
	@Column(length = 2048)
	private String content;
	
	
	
	//@Column(name="board_type")
	@Column
	@Enumerated(EnumType.STRING)
	private BoardType boardType;
	
	
	//private LocalDateTime created_at;
	
	@Builder
	public BoardEntity(Long board_id, String nickname, String title, String content, BoardType boardType) {
		this.board_id = board_id;
		this.nickname = nickname;
		this.title = title;
		this.content = content;
		this.boardType = boardType;
		
	}
	
}
