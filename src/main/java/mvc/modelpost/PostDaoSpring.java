package mvc.modelpost;

import java.sql.*;
import javax.sql.*;

import org.springframework.jdbc.core.JdbcTemplate;

public class PostDaoSpring extends PostDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private JdbcTemplate jdbcTemplate;
	
	public PostDaoSpring(DataSource dstm) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dstm);
	}

	public void search(String keyword, String category) {
	    String sql = "SELECT * FROM post WHERE ";
	    if (category.equals("title")) {					// 제목 검색
	        sql += "title LIKE '%" + keyword + "%'";
	    } else if (category.equals("content")) {		// 내용 검색
	        sql += "post_content LIKE '%" + keyword + "%'";
	    } else if (category.equals("author")) {			// 작성자 검색
	        sql += "member_id IN (SELECT member_id FROM member WHERE name LIKE '%" + keyword + "%')";
	    } else if (category.equals("all")) {			// 제목+내용 검색
	        sql += "title LIKE '%" + keyword + "%' OR post_content LIKE '%" + keyword + "%' OR member_id IN (SELECT member_id FROM member WHERE name LIKE '%" + keyword + "%')";
	    }
	    
	    try {
	    	pstmt = conn.prepareStatement(sql);
	        if (category.equals("all")) {
	            pstmt.setString(1, "%" + keyword + "%");
	            pstmt.setString(2, "%" + keyword + "%");
	            pstmt.setString(3, "%" + keyword + "%");
	        } else {
	            pstmt.setString(1, "%" + keyword + "%");
	        }
	        pstmt.executeQuery();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insert(PostDO postDO) {
	    String sql = "INSERT INTO post (post_id, category, title, member_id, post_content) "
	            + "VALUES (post_post_id_seq.NEXTVAL, ?, ?, ?, ?)";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, postDO.getCategory());
	        pstmt.setString(2, postDO.getTitle());
	        pstmt.setInt(3, postDO.getMemberId());
	        pstmt.setString(4, postDO.getContent());
	        pstmt.executeUpdate();
	    } 
	    catch(Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if(!pstmt.isClosed()) {
	                pstmt.close();
	            }
	        } 
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public void update(PostDO postDO) {
	    String sql = "UPDATE post SET post_content=? WHERE post_id=?";
	   
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, postDO.getContent());
	        pstmt.setInt(2, postDO.getPostId());
	        pstmt.executeUpdate();
	    } 
	    catch(Exception e) {
	    	e.printStackTrace();
	    } 
	    finally {
	        try {
	            if(!pstmt.isClosed()) {
	                pstmt.close();
	            }
	        } 
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void delete(PostDO postDO) {
	    String sql = "DELETE FROM post WHERE post_id=?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, postDO.getPostId());
	        pstmt.executeUpdate();
	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    } 
	    finally {
	        try {
	            if (pstmt != null) {
	                pstmt.close();
	            }
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public int recommend(PostDO postDO) {
	    int result = 0;
	    String sql = "UPDATE post SET like_num = like_num + 1 WHERE post_id = ?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, postDO.getPostId());
	        result = pstmt.executeUpdate();
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if(!pstmt.isClosed()) {
	                pstmt.close();
	            }
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}

	public int unrecommend(PostDO postDO) {
	    int result = 0;
	    String sql = "UPDATE post SET dislike_num = dislike_num + 1 WHERE post_id = ?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, postDO.getPostId());
	        result = pstmt.executeUpdate();
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if(!pstmt.isClosed()) {
	                pstmt.close();
	            }
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}

	public int report(PostDO postDO) {
	    int result = 0;
	    String sql = "UPDATE post SET report_num = report_num + 1 WHERE post_id = ?";

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, postDO.getPostId());
	        result = pstmt.executeUpdate();
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	        try {
	            if(!pstmt.isClosed()) {
	                pstmt.close();
	            }
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return result;
	}
	
	public void notice(PostDO postDO) {
	    String sql = "UPDATE post SET notice = 1 WHERE post_id = ?";
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, postDO.getPostId());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) {
	                pstmt.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}
