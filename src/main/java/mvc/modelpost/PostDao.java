package mvc.modelpost;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import mvc.model.Member;
import mvc.model.MemberDao;

@Repository
public class PostDao {
	
    private static long nextId = 0;
    private Map<Long, PostDO> posts;

    public PostDao() {
        this.posts = new HashMap<>();
    }
    
    /*
    public PostDO search(long postId) {
        return posts.get(postId);
    }

    public void insert(PostDO postDO) {
        postDO.setPostId(++nextId);
        posts.put(postDO.getPostId(), postDO);
    }

    public void update(PostDO postDO, String content) {
        if (postDO != null) {
            postDO.setContent(content);
            posts.put(postDO.getPostId(), postDO);
        }
    }

    public void delete(PostDO postDO) {
    	posts.remove(postDO.getPostId());
    }

    public void recommend(long postId) {
        PostDO postDO = posts.get(postId);
        if (postDO != null) {
            postDO.setLikeNum(postDO.getLikeNum() + 1);
        }
    }

    public void unrecommend(long postId) {
        PostDO postDO = posts.get(postId);
        if (postDO != null) {
            postDO.setDislikeNum(postDO.getDislikeNum() + 1);
        }
    }

    public void report(long postId) {
        PostDO postDO = posts.get(postId);
        if (postDO != null) {
            postDO.setReportNum(postDO.getReportNum() + 1);
        }
    }
    
    public void notice(long postId) {
        PostDO postDO = posts.get(postId);
        if (postDO != null) {
            postDO.setNotice(1);
        }
    }

    public Map<Long, PostDO> getPostMap() {
        return posts;
    }
    */
    
    public List<PostDO> listByCategory(String category, Criteria cri) {
    	List<PostDO> list = null;
    	
		return list;
    }
    
    public int getListCount() {
    	int count = 0;
    	
		return count;
	}
    
    public PostDO searchById(long postId) {
        return posts.get(postId);
    }
    
    public void insert(PostDO postDO) {
    	postDO.setPostId(++PostDao.nextId);
		
		posts.put(postDO.getPostId(), postDO);
	}
    
    public void update(PostDO postDO) {
		
    	posts.put(postDO.getPostId(), postDO);
	}

	public void delete(PostDO postDO) { 	
		
		posts.put(postDO.getPostId(), postDO);
	}
}
