package modelpost;

public class PostRecommendSO {
    private PostDao postDao;
    private PostDO postDO;

    public PostRecommendSO(PostDao postDao, PostDO postDO) {
        this.postDao = postDao;
        this.postDO = postDO;
    }

    public PostRecommendSO(PostDaoSpring postDaoSpring) {
	}

	public int postLike(int postId, int userId, boolean like) {
        PostDO postDO = postDao.search(postId);
        if (postDO == null) {
            throw new RuntimeException("Post not found in database");
        }

        if (like) {
            postDao.recommend(postId);
            return postDO.getLikeNum();
        } else {
            postDao.unrecommend(postId);
            return postDO.getDislikeNum();
        }
    }
}