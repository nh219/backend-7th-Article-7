package mvc.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String loginPost(LoginCommand command, HttpSession session,RedirectAttributes model) {
	
		System.out.println(command);
		if(memberService.checkLoginAuth(command)) {
			Member member = memberService.findMember(command.getEmail());
			session.setAttribute("auth", member);		// auth에 parameter로 넘어온 command의 이름과 이메일만 저장.
			return "redirect:/main";
		}
		else {
			model.addFlashAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
			return "redirect:/member/login";
		}
			
	
	}
	
	@GetMapping("/member/login")
	public String login() {

		return "/member/login";
	}
	
	@PostMapping("/member/memberUpdateProcess")
	public String memberUpdate(HttpSession session, MemberUpdateCommand command, Model model) {
		String view = "";
		
		try {
			memberUpdateService.memberUpdate(command);
			session.invalidate();
			view = "redirect:/main";
		} catch (Exception e) {
			model.addAttribute("changePasswdMsg", "올바른 비밀번호를 입력해주세요.");
			view = "member/memberUpdate";
		}
		
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/nicknameDoubleCheck", method = RequestMethod.POST)
	public int nicknameDoubleCheck(Member member) throws Exception {
		int result = memberService.nameCheck(member.getNickname());
		
		return result;
	}
	
	@GetMapping("/member/memberWithdrawalProcess")
	public String removeMember(HttpSession session) {
		String view = "";
		
		Member member = (Member)session.getAttribute("auth");
		memberUpdateService.memberWithdrawal(member);
		
		session.invalidate();
		view = "redirect:/main";
		
		return view;
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		String view = "";
		
		session.invalidate();
		view = "redirect:/member/login";
		
		
		return view;
 	}
}
