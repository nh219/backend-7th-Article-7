package mvc.model;

import java.util.HashMap;
import java.util.Map;

public class PostDao {

    private static int nextId = 0;
    private Map<Integer, PostDO> postMap;

    public PostDao() {
        this.postMap = new HashMap<>();
    }

    public PostDO selectByPostId(int postId) {
        return postMap.get(postId);
    }

    public void insert(PostDO postDO) {
        postDO.setPostId(++nextId);
        postMap.put(postDO.getPostId(), postDO);
    }

    public void update(int postId, String content) {
        PostDO postDO = postMap.get(postId);
        if (postDO != null) {
            postDO.setContent(content);
        }
    }

    public void delete(PostDO postDO) {
        postMap.remove(postDO.getPostId());
    }
}
