package mvc.model;

public class PostReportSO {
    private PostDao postDao;

    public PostReportSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public int report(int postId) {
        PostDO postDO = postDao.search(postId);
        if (postDO != null) {
            postDO.setReportNum(postDO.getReportNum() + 1);
            return postDO.getReportNum();
        } else {
            return -1; // 해당하는 게시물이 없으면 -1 반환.
        }
    }
}
