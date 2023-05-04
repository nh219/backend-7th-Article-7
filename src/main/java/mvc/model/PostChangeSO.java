package mvc.model;

public class PostChangeSO {
    private PostDAO postDao;

    public PostChangeSO() {
        postDao = new PostDAO(dataSource);
    }

    public PostChangeSO(PostDAO postDao) {
        this.postDao = postDao;
    }

    public boolean update(PostUpdateCommand command) {
        int postId = command.getPostId();
        PostDO existingPost = postDao.selectById(postId);

        if (existingPost == null) {
            return false;
        }

        String newTitle = command.getTitle();
        String newContent = command.getContent();

        if (newTitle != null && !newTitle.equals(existingPost.getTitle())) {
            existingPost.changeTitle(existingPost.getTitle(), newTitle);
        }

        if (newContent != null && !newContent.equals(existingPost.getContent())) {
            existingPost.changeContent(existingPost.getContent(), newContent);
        }

        if (existingPost.getTitle().equals(command.getTitle()) && existingPost.getContent().equals(command.getContent())) {
            return false;
        }

        return postDao.postUpdateAll(existingPost);
    }

    public boolean delete(int postId) {
        PostDO existingPost = postDao.selectById(postId);

        if (existingPost == null) {
            return false;
        }

        return postDao.postDeleteById(postId);
    }
}