package modelpost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostSearchSO {
    private PostDao postDao;

    public PostSearchSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public List<PostDO> search(String keyword) {
        List<PostDO> result = new ArrayList<>();
        Map<Integer, PostDO> postMap = postDao.getPostMap();

        for (PostDO postDO : postMap.values()) {
            if (postDO.getTitle().contains(keyword) || postDO.getContent().contains(keyword)) {
                result.add(postDO);
            }
        }

        return result;
    }
    
}
