package mvc.model;

import java.util.Date;

public class PostChangeSO {
    private PostDao postDao;

    public PostChangeSO() {
        this.postDao = new PostDao();
    }

    public PostChangeSO(PostDao postDao) {
        this.postDao = postDao;
    }

    public void update(PostUpdateCommand command) {
        PostDO postDO = postDao.search(command.getPostId());
        if (postDO != null) {
            if (command.getCategory() != null) {
                postDO.setCategory(command.getCategory());
            }
            if (command.getTitle() != null) {
                postDO.setTitle(command.getTitle());
            }
            if (command.getContent() != null) {
                postDO.setContent(command.getContent());
            }
            postDO.setPost_date(new Date());
            postDao.update(postDO);
            System.out.println("게시물이 수정되었습니다.");
        } else {
            System.out.println("해당 게시물이 존재하지 않습니다.");
        }
    }

    public void delete(int postId) {
        PostDO postDO = postDao.search(postId);
        if (postDO != null) {
            postDao.delete(postDO);
            System.out.println("게시물이 삭제되었습니다.");
        } else {
            System.out.println("해당 게시물이 존재하지 않습니다.");
        }
    }

    public void recommend(int postId) {
        postDao.recommend(postId);
    }

    public void unrecommend(int postId) {
        postDao.unrecommend(postId);
    }

    public void report(int postId) {
        postDao.report(postId);
    }

    public void notice(int postId) {
        postDao.notice(postId);
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}