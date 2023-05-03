package mvc.model;

public class PostRegistSO {
	private PostDao postDao;

    public PostRegistSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public boolean regist(Post post) {
        if (!checkContent(post.getContent())) {
            return false;
        }

        if (!postDao.insert(post)) {
            throw new RuntimeException("Failed to insert post into database");
        }

        return true;
    }

    private boolean checkContent(String content) {
        return content != null && !content.trim().isEmpty();
    }
}
