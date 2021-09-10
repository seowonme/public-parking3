package teamD.publicParking.user.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import teamD.publicParking.user.domain.Role;
import teamD.publicParking.user.domain.entity.Member;
import teamD.publicParking.user.domain.repository.MemberRepository;
import teamD.publicParking.user.dto.MemberDto;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

	private MemberRepository memberRepository;
	
//	회원가입
	@Transactional
	public Long signUp(MemberDto memberDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		
//		password를 암호화 한 뒤 db에 저장
		
		return memberRepository.save(memberDto.toEntity()).getUser_id();
	}
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		로그인을 하기 위해 가입된 user정보를 조회하는 메서드
		Optional<Member> memberWrapper = memberRepository.findByEmail(email);
		Member member = memberWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("admin".equals(email)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
//		아이디, 비밀번호, 권한리스트를 매개변수로 User를 만들어 반환해준다.
		return new User(member.getEmail(), member.getPassword(), authorities);
	}
	

}
