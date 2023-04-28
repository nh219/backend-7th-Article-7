package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mvc.exception.*;
import mvc.model.*;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@PostMapping("/register/step2")	// http://localhost:8080/finalProject/register/step2
	public String handleStep2(@RequestParam(value="agree", defaultValue="false") boolean agree
			, Model model) {
		String view = "";
		
		if(agree) {
			view = "register/step2";
			model.addAttribute("formData", new RegistCommand());
			//Spring form tag에서 formData객체를 사용하므로 해당 속성의 객체가 존재하지 않는 경우 오류! 
		}
		else {
			view = "redirect:/register/step1";
		}
		
		return view;
	}
	
	@GetMapping("/register/step2") // 한번에 step2로 넘어가려할 때 step1 화면을 반환
	public String handleStep2Get() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/register/step3")	 
	public String handleStep3(@ModelAttribute("formData") RegistCommand regReq, Model model) {
		// request 객체인 model도 바로 불러서 사용 가능!
		
		String view = "register/step3";
		
		try {
			memberRegisterService.regist(regReq);
		}
		catch(DuplicateMemberException e) {
			model.addAttribute("msg", "중복된 이메일 주소가 발견되었습니다.");
			view = "register/step2";
		}
		
		return view;
	}
	
}
