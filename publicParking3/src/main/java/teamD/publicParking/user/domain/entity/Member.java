package teamD.publicParking.user.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	@Column(length=20, nullable=false)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 10, nullable = false)
	private String nickname;
	
	@Builder
	public Member(Long user_id, String email, String password, String nickname) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
	
	
}
