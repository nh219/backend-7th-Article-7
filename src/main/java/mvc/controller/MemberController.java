package mvc.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.LoginCommand;
import mvc.model.Member;
import mvc.model.MemberService;
import mvc.model.MemberUpdateCommand;
import mvc.model.MemberUpdateService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	@PostMapping("/member/loginProcess")
	public String loginPost(LoginCommand command, HttpSession session) {
		String view = "";
		
		if(memberService.checkLoginAuth(command)) {
			Member member = memberService.findMember(command.getEmail());
			command.setNickname(member.getNickname());
			command.setPassword("");
			session.setAttribute("auth", command);		// auth에 parameter로 넘어온 command의 이름과 이메일만 저장.
			view = "redirect:/main";
		}
		else {
			session.setAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
			view = "redirect:/member/login";
		}
			
		return view;
	}
	
	@PostMapping("/member/memberUpdateProcess")
	public String memberUpdate(MemberUpdateCommand command, Model model) {
		String view = "";
		
		try {
			memberUpdateService.memberUpdate(command);
			view = "redirect:/main";
		} catch (Exception e) {
			model.addAttribute("changePasswdMsg", "올바른 비밀번호를 입력해주세요.");
			view = "member/memberUpdate";
		}
		
		return view;
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		String view = "";
		
		if(session.getAttribute("auth") != null) {
			session.invalidate();
			view = "redirect:/main";
		}
		else {
			view = "redirect:/main";
		}
		
		return view;
 	}
}
