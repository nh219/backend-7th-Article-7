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
	public String loginPost(LoginCommand command, HttpSession session, RedirectAttributes model) {

	    System.out.println(command);

	    // 로그인 인증 체크
	    if (memberService.checkLoginAuth(command)) {
	        // 이메일을 사용하여 회원 정보 가져오기
	        Member member = memberService.findMember(command.getEmail());

	        if (member != null) {
	            // 로그인된 회원의 정보를 HttpSession에 저장
	            session.setAttribute("auth", member);

	            return "redirect:/main";
	        } else {
	            model.addFlashAttribute("loginFailMsg", "로그인에 실패했습니다. 다시 시도해주세요.");
	            return "redirect:/member/login";
	        }
	    } else {
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
		view = "redirect:/main";
		
		
		return view;
 	}
	/*
	@GetMapping("/post/postRegist")
	public String showPage(Model model, HttpSession session) {
	    Member member = (Member) session.getAttribute("member_id");

	    if (member == null) {
	        session.setAttribute("alert", "로그인 후 이용하실 수 있습니다.");
	        return "redirect:/member/login"; // 로그인 페이지로 리디렉션
	    }

	    model.addAttribute("member_id", member.getId());
	    return "/post/postRegist";
	}
*/

}
