package mvc.modelpost;

import java.util.List;

import mvc.modelpost.PostDO;
import mvc.modelpost.PostDao;

public class PostService {
	
	private PostDao postDao;
	
	public PostService(PostDao postDao) {
		this.postDao = postDao;
	}
	
	public List<PostDO> findPostByCategory(String category){
		List<PostDO> list = postDao.listByCategory(category);
		
		return list;
	}
}
