package teamD.publicParking.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import teamD.publicParking.user.domain.service.MemberService;
import teamD.publicParking.user.dto.MemberDto;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {
	private MemberService memberService;
	
//	메인
	@GetMapping("/")
	public String index() {
		return "/index";
	}
	
//	회원가입
	@GetMapping("/user/signup") 
	public String dispSignup() {
		return "/signup";
	}
	
    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/member/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }

    // 로그인 결과 페이지 -> 메인 이동
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "redirect:/index";
    }
    
    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/mypage")
    public String dispMyInfo() {
        return "/mypage";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
}
