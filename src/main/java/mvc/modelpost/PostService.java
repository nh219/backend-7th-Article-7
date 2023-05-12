package mvc.modelpost;

import java.util.Date;
import java.util.List;

import mvc.modelpost.*;

public class PostService {
	
	private PostDao postDao;
	
	public PostService(PostDao postDao) {
		this.postDao = postDao;
	}
	
	public List<PostDO> findPostByCategory(String category){
		List<PostDO> list = postDao.listByCategory(category);
		
		return list;
	}
	
	public PostDO findPostById(long postId) {
		PostDO postDO = postDao.searchById(postId);
		
		return postDO;
	}
	
	public long regist(PostRegistCommand req) {		// 실제 회원 등록을 수행하는 메서드
		Date date = new Date();
		
		PostDO postDO = new PostDO(req.getPostId(), req.getCategory(), req.getTitle(), req.getNickname(), req.getContent(),
						0, 0, 0, 0, 0, date);
		
		postDao.insert(postDO);
		
		return postDO.getPostId();			// 생성된 member의 nextId를 반환
	}
}
