package mvc.modelpost;

public class PostRegistSO {
    private PostDao postDao;

    public PostRegistSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public boolean regist(PostDO postDO) {
        if (!checkContent(postDO.getContent())) {
            return false;
        }

        if (!postDao.insert(postDO)) {
            throw new RuntimeException("Failed to insert post into database");
        }
        return true;
    }

    private boolean checkContent(String content) {
        return content != null && !content.trim().isEmpty();
    }
    
}
