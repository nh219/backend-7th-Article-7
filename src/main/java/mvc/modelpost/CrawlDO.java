package mvc.modelpost;

public class CrawlDO {

		private String url;
		private String siteName;
		private String title;
		private String nickName;
		private String date;
		private int views;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getSiteName() {
			return siteName;
		}
		public void setSiteName(String siteName) {
			this.siteName = siteName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getNickName() {
			return nickName;
		}
		public void setNickName(String nickName) {
			this.nickName = nickName;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getViews() {
			return views;
		}
		public void setViews(int views){
			this.views = views;
			}
		/*
		​
		SELECT * FROM (
		  SELECT url, sitename, title, nickname, c_post_date, views
		  FROM crawl_post
		  ORDER BY views DESC
		)
		WHERE ROWNUM <= 6; 
		​
		 */

	}