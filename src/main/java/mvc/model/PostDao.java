package mvc.model;

import java.util.HashMap;
import java.util.Map;

public class PostDao {

	private static int nextId = 0;	
	private Map<Integer, PostDO> post;
	
	public PostDao() {
		this.post = new HashMap<>();		
	}
	
	public PostDO selectByPostId(Integer postId) {
		return post.get(postId);
	}
	
	public void insert(PostDO postDO) {		
		postDO.setPostId(++PostDao.nextId);
		post.put(postDO.getPostId(), postDO);
	}
	
	public void update(PostDO postDO) { 	
		post.put(postDO.getPostId(), postDO);
	}

	public void delete(PostDO postDO) { 	
		post.put(postDO.getPostId(), postDO);
	}

}
