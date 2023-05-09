package mvc.controller;

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

import mvc.modelpost.*;;

@Controller
public class PostController {
	
    @Autowired
    private PostDao postDao;

    @GetMapping("/post/{postId}")
    public String getPost(@PathVariable int postId, Model model) {
        try {
            PostDO postDO = postDao.search(postId);
            model.addAttribute("post", postDO);
            return "post";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute PostDO postDO) {
        try {
            postDao.insert(postDO);
            return "redirect:/Post/postRegist";
        } catch (Exception e) {
            return "error";
        }
    }

    @PutMapping("/post/{postId}")
    public String updatePost(@PathVariable int postId, @RequestParam String content) {
        try {
            PostDO postDO = postDao.search(postId);
            postDao.update(postDO, content);
            return "redirect:/post/postRegist" + postId;
        } catch (Exception e) {
            return "error";
        }
    }

    @DeleteMapping("/post/{postId}")
    public String deletePost(@PathVariable int postId) {
        try {
            PostDO postDO = postDao.search(postId);
            postDao.delete(postDO);
            return "redirect:/post/postRegist";
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/post/{postId}/like")
    public String recommendPost(@PathVariable int postId) {
        try {
            postDao.recommend(postId);
            return "redirect:/post/postContent" + postId;
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/post/{postId}/dislike")
    public String unrecommendPost(@PathVariable int postId) {
        try {
            postDao.unrecommend(postId);
            return "redirect:/post/postContent" + postId;
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/post/{postId}/report")
    public String reportPost(@PathVariable int postId) {
        try {
            postDao.report(postId);
            return "redirect:/post/postContent" + postId;
        } catch (Exception e) {
            return "error";
        }
    }
    
    @PostMapping("/post/{postId}/notice")
    public String noticePost(@PathVariable int postId) {
        try {
            postDao.notice(postId);
            return "redirect:/post/postContent" + postId;
        } catch (Exception e) {
            return "error";
        }
    }
    
}
