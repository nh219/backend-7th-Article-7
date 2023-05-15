package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mvc.exception.*;
import mvc.model.*;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}


	
	@GetMapping("/register/step2") // 한번에 step2로 넘어가려할 때 step1 화면을 반환
	public String handleStep2Get() {
		return "register/step2";
	}
	
	
	@GetMapping("/register/step1") 
	public String handleStep1Get() {
		return "/register/step1";
	}
	
	@PostMapping("/register/step2")	 
	public String handleStep3(@ModelAttribute("formData") RegistCommand regReq, RedirectAttributes model) {
		// request 객체인 model도 바로 불러서 사용 가능!
		
	
		System.out.println(regReq);
	    try {
	        System.out.println("rkdlq");
	        if (!regReq.getPassword().equals(regReq.getConfirmPassword())) {
	            model.addFlashAttribute("msg", "비밀번호가 일치하지 않습니다.");
	            return "redirect:/register/step2";
	        }
	        memberRegisterService.regist(regReq);
	    } catch (DuplicateMemberException e) {
	        model.addFlashAttribute("msg", "중복된 이메일 주소가 발견되었습니다.");
	        return "redirect:/register/step2";
	    }
	    return "redirect:/main";
	}
	
}
