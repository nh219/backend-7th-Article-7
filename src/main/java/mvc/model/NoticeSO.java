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

        PostDO postDO = postDao.search(postId);						 // 게시물 아이디로 게시물 검색
        if (postDO != null && postDO.getMemberId() == userId) { 	 
            postDO.setNotice(notice);								 // 게시물을 작성한 사용자와 알림을 추가하려는 사용자가 같으면 알림 상태를 변경 (관리자는...?)
            postDao.update(postId, postDO.getContent()); 			 // 변경된 게시물 정보를 데이터베이스에 업데이트
        }
    }
    
}
