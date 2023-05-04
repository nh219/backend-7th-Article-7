package mvc.model;

public class NoticeSO {
	
    private PostDao postDao;
    private Member member;

    public NoticeSO(PostDao postDao, Member member) {
        this.postDao = postDao;
        this.member = member;
    }

    public void notice(int postId) {
        PostDO postDO = postDao.search(postId);
        if (postDO != null && member.isAdmin()) {
            postDO.setNotice(1);
            postDao.update(postDO, postDO.getContent());
        }
    }
}
