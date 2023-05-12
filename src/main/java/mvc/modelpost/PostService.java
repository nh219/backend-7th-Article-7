package mvc.modelpost;

import java.util.Date;
import java.util.List;

import mvc.modelpost.PostDO;
import mvc.modelpost.PostDao;

public class PostService {
	
	private PostDao postDao;
	
	public PostService(PostDao postDao) {
		this.postDao = postDao;
	}
	
	public List<PostDO> listByCategory(String category, Criteria cri){
		List<PostDO> list = postDao.listByCategory(category, cri);
		
		return list;
	}
	
	public int listCount() {
		return postDao.getListCount();
	}
	
	public PostDO findPostById(long postId) {
		PostDO postDO = postDao.searchById(postId);
		
		return postDO;
	}
	
	public long regist(PostRegistCommand req) {
		Date date = new Date();
		
		PostDO postDO = new PostDO(req.getPostId(), "free", req.getTitle(), req.getNickname(), req.getContent(),
						0, 0, 0, 0, 0, date);
		
		postDao.insert(postDO);
		
		return postDO.getPostId();
	}
}
