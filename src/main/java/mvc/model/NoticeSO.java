package mvc.model;

public class NoticeSO {

    private PostDao postDao;

    public NoticeSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public void notice(NoticeCommand command) {
        int userId = command.getUserId();
        int postId = command.getPostId();
        int notice = command.getNotice();

        PostDO postDO = postDao.search(postId);
        if (postDO != null && postDO.getMemberId() == userId) {
            postDO.setNotice(notice);
            postDao.update(postDO);
        }
    }
}

// Notice PostDao &PostDaoSpring에 notice 만들기