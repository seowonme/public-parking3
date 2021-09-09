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
	
	@Transactional
	public Long joinUser(MemberDto memberDto) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		
		return memberRepository.save(memberDto.toEntity()).getUser_id();
	}
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Optional<Member> userEntiryWrapper = memberRepository.findByEmail(userEmail);
		Member member = userEntiryWrapper.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(("admin@example.com").equals(userEmail)) {
			authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getEmail(), member.getPassword(), authorities);
	}
}
