package mvc.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PostDao {
    private static int nextId = 0;
    private Map<Integer, PostDO> postMap;

    public PostDao() {
        this.postMap = new HashMap<>();
    }

    public PostDO search(int postId) {
        return postMap.get(postId);
    }

    public void insert(PostDO postDO) {
        postDO.setPostId(++nextId);
        postMap.put(postDO.getPostId(), postDO);
    }

    public void update(PostDO postDO, String content) {
        if (postDO != null) {
            postDO.setContent(content);
            postMap.put(postDO.getPostId(), postDO);
        }
    }

    public void delete(PostDO postDO) {
        postMap.remove(postDO.getPostId());
    }

    public void recommend(int postId) {
        PostDO postDO = postMap.get(postId);
        if (postDO != null) {
            postDO.setLikeNum(postDO.getLikeNum() + 1);
        }
    }

    public void unrecommend(int postId) {
        PostDO postDO = postMap.get(postId);
        if (postDO != null) {
            postDO.setDislikeNum(postDO.getDislikeNum() + 1);
        }
    }

    public void report(int postId) {
        PostDO postDO = postMap.get(postId);
        if (postDO != null) {
            postDO.setReportNum(postDO.getReportNum() + 1);
        }
    }
    
    public void notice(int postId) {
        PostDO postDO = postMap.get(postId);
        if (postDO != null) {
            postDO.setNotice(1);
        }
    }

    public Map<Integer, PostDO> getPostMap() {
        return postMap;
    }

}
