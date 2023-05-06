package mvc.model;

public class PostChangeSO {
    private PostDao postDao;
    
    public PostChangeSO() {
        postDao = new PostDao();
    }
    
    public PostChangeSO(PostDao postDao) {
        this.postDao = postDao;
    }
    
    public void update(PostUpdateCommand command) {
        PostDO postDO = postDao.search(command.getPostId());
        if (postDO != null) {
            postDao.update(postDO, command.getContent());
            System.out.println("게시물이 수정되었습니다.");
        } else {
            System.out.println("해당 게시물이 존재하지 않습니다.");
        }
    }
    
    public void delete(int postId) {
        PostDO postDO = postDao.search(postId);
        if (postDO != null) {
            postDao.delete(postDO);
        } else {
            System.out.println("해당 게시물이 존재하지 않습니다.");
        }
    }
}