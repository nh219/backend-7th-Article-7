package mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.util.StringUtils;

import mvc.exception.DuplicateMemberException;
import mvc.model.MemberService;
import mvc.model.RegistCommand;
import mvc.modelpost.*;;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostUpdateService postUpdateService;

	private PostDO postDO;
    
    @GetMapping("/post/community")
	public String listCommunity(Model model) throws Exception{
		String view = "";
		view = "/post/community";
		model.addAttribute("list", postService.listAll());
		
		return view;
 	}
    
    @GetMapping("/post/postFree")
    public String listFree(Model model, String category) throws Exception{
    	String view = "";
    	view = "/post/postFree";
    	category = "자유";
    	model.addAttribute("list", postService.findPostByCategory(category));
    	
    	return view;
    }
    
    @GetMapping("/post/postVote")
    public String listVote(Model model, String category) throws Exception{
    	String view = "";
    	view = "/post/postVote";
    	category = "투표 피드백";
    	model.addAttribute("list", postService.findPostByCategory(category));
    	
    	return view;
    }
    
    @GetMapping("/post/postParty")
    public String listParty(Model model, String category) throws Exception{
    	String view = "";
    	view = "/post/community";
    	category = "파티 구함";
    	model.addAttribute("list", postService.findPostByCategory(category));
    	
    	return view;
    }
    
    @GetMapping("/post/postCrawl")
    public String listCrawl(Model model, String category) throws Exception{
    	String view = "";
    	view = "/post/postCrawl";
    	category = "통합 인기 게시판";
    	model.addAttribute("list", postService.findPostByCategory(category));
    	
    	return view;
    }
    
    @GetMapping("/post/postContent")
	public String read(PostDO postDO, Model model) throws Exception{
		String view = "";
		view = "/post/postContent";
		
		model.addAttribute("read", postService.findPostById(postDO.getPostId()));
		
		return view;
 	}
    
    @PostMapping("/post/postRegistProcess")	 
	public String writeProcess(@ModelAttribute("formData") PostRegistCommand regReq, Model model) {
		String view = "redirect:/post/community";
		
		postService.regist(regReq);
		
		return view;
	}
    
    @GetMapping("/post/postUpdate")
	public String update(PostDO postDO, Model model) throws Exception{
		String view = "";
		view = "/post/postUpdate";
		
		model.addAttribute("update", postService.findPostById(postDO.getPostId()));
		
		return view;
 	}
    
    @PostMapping("/post/postUpdateProcess")
   	public String updateProcess(PostDO postDO) throws Exception{
   		String view = "";
   		view = "redirect:/post/community";
   		
   		postUpdateService.postUpdate(postDO);
   		
   		return view;
    }
    
    @PostMapping("/post/postDeleteProcess")
   	public String deleteProcess(PostDO postDO) throws Exception{
   		String view = "";
   		view = "redirect:/post/community";
   		
   		postUpdateService.postDelete(postDO);
   		
   		return view;
    }
    
    /*
    // 게시글 조회수 증가 처리
    @PostMapping("/post/viewsUpdateProcess")
    public String updateProcess(@RequestParam("postId") String postId) throws Exception {
        postService.updatePostCount(postDO);
        return "redirect:/post/community";
    }
    */

    /*
    @GetMapping("/post/{postId}")
    public ModelAndView getPost(@PathVariable("postId") int postId) {
        // 조회수 증가
        postService.increaseViewCount(postId);
        
        // 게시물 조회
        PostDO postDO = postService.findPostById(postId);

        // 모델 생성
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", postDO);
        modelAndView.setViewName("post/postContent");
        
        return modelAndView;
    }
*/

    
}





//@Autowired
//private PostDao postDao;
//private PostDaoSpring postDaoSpring;
//
//@GetMapping("/post/{postId}")
//public String getPost(@PathVariable int postId, Model model) {
//  try {
//      PostDO postDO = postDao.search(postId);
//      model.addAttribute("post", postDO);
//      return "post";
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PostMapping("/post")
//public String createPost(@ModelAttribute PostDO postDO) {
//  try {
//      postDao.insert(postDO);
//      return "redirect:/Post/postRegist";
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PutMapping("/post/{postId}")
//public String updatePost(@PathVariable int postId, @RequestParam String content) {
//  try {
//      PostDO postDO = postDao.search(postId);
//      postDao.update(postDO, content);
//      return "redirect:/post/postRegist" + postId;
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@DeleteMapping("/post/{postId}")
//public String deletePost(@PathVariable int postId) {
//  try {
//      PostDO postDO = postDao.search(postId);
//      postDao.delete(postDO);
//      return "redirect:/post/postRegist";
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PostMapping("/post/{postId}/like")
//public String recommendPost(@PathVariable int postId) {
//  try {
//      postDao.recommend(postId);
//      return "redirect:/post/postContent" + postId;
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PostMapping("/post/{postId}/dislike")
//public String unrecommendPost(@PathVariable int postId) {
//  try {
//      postDao.unrecommend(postId);
//      return "redirect:/post/postContent" + postId;
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PostMapping("/post/{postId}/report")
//public String reportPost(@PathVariable int postId) {
//  try {
//      postDao.report(postId);
//      return "redirect:/post/postContent" + postId;
//  } catch (Exception e) {
//      return "error";
//  }
//}
//
//@PostMapping("/post/{postId}/notice")
//public String noticePost(@PathVariable int postId) {
//  try {
//      postDao.notice(postId);
//      return "redirect:/post/postContent" + postId;
//  } catch (Exception e) {
//      return "error";
//  }
//}