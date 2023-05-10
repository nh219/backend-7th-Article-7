package mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.modelpost.PostDO;
import mvc.modelpost.PostRegistCommand;
import mvc.modelpost.PostService;;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	  
//    @Autowired
//    private PostDao postDao;
//    private PostDaoSpring postDaoSpring;
//
//    @GetMapping("/post/{postId}")
//    public String getPost(@PathVariable int postId, Model model) {
//        try {
//            PostDO postDO = postDao.search(postId);
//            model.addAttribute("post", postDO);
//            return "post";
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @PostMapping("/post")
//    public String createPost(@ModelAttribute PostDO postDO) {
//        try {
//            postDao.insert(postDO);
//            return "redirect:/Post/postRegist";
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @PutMapping("/post/{postId}")
//    public String updatePost(@PathVariable int postId, @RequestParam String content) {
//        try {
//            PostDO postDO = postDao.search(postId);
//            postDao.update(postDO, content);
//            return "redirect:/post/postRegist" + postId;
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @DeleteMapping("/post/{postId}")
//    public String deletePost(@PathVariable int postId) {
//        try {
//            PostDO postDO = postDao.search(postId);
//            postDao.delete(postDO);
//            return "redirect:/post/postRegist";
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @PostMapping("/post/{postId}/like")
//    public String recommendPost(@PathVariable int postId) {
//        try {
//            postDao.recommend(postId);
//            return "redirect:/post/postContent" + postId;
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @PostMapping("/post/{postId}/dislike")
//    public String unrecommendPost(@PathVariable int postId) {
//        try {
//            postDao.unrecommend(postId);
//            return "redirect:/post/postContent" + postId;
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//
//    @PostMapping("/post/{postId}/report")
//    public String reportPost(@PathVariable int postId) {
//        try {
//            postDao.report(postId);
//            return "redirect:/post/postContent" + postId;
//        } catch (Exception e) {
//            return "error";
//        }
//    }
//    
//    @PostMapping("/post/{postId}/notice")
//    public String noticePost(@PathVariable int postId) {
//        try {
//            postDao.notice(postId);
//            return "redirect:/post/postContent" + postId;
//        } catch (Exception e) {
//            return "error";
//        }
//    }
    
    @GetMapping("/post/postListTest")
	public String list(Model model, String category) throws Exception{
		String view = "";
		view = "/post/postListTest";
		category = "free";
		model.addAttribute("list", postService.findPostByCategory(category));
		
		return view;
 	}
    
    @GetMapping("/post/postReadTest")
	public String read(PostDO postDO, Model model) throws Exception{
		String view = "";
		view = "/post/postReadTest";
		
		model.addAttribute("read", postService.findPostById(postDO.getPostId()));
		
		return view;
 	}
    
    @PostMapping("/post/postWriteProcess")	 
	public String handleStep3(@ModelAttribute("formData") PostRegistCommand regReq, Model model) {
		String view = "redirect:/post/postListTest";
		
		postService.regist(regReq);
		
		return view;
	}
    

}