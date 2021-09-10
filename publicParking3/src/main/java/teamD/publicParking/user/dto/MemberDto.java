package teamD.publicParking.user.dto;

import teamD.publicParking.user.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

	private Long user_id;
	private String email;
	private String password;
	private String nickname;
	
	public Member toEntity() {
		return Member.builder()

                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
	}
	
	@Builder
	public MemberDto(Long user_id, String email, String password, String nickname) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
				
	}
	
}
