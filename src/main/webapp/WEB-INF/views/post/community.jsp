<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Teamluck.gg</title>
	<link rel="stylesheet" href="../WebContent/community style copy.css">
</head>
<body>
	<!-- 헤더 영역 -->
	<header>
		<div class="logo">
			<a href="http://Teamluck.gg.com"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>

		<div class="navi">
			<nav>
				<ul>
					<li><a href="#">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="#">통합 인기글</a></li>
					<li><a href="#">로그아웃</a></li>
				</ul>
			</nav>
		</div>
	</header>
    <main>
		<div class="main section">
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="https://i.ibb.co/fDR0YnX/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
					<img src="banner_left.png" alt="Banner Left">
				</div>

				<!-- 콘텐츠 영역 -->
			    <div class="content1">
                    <div class="sidebar" style>
                        <div class="sidebar__inner" style="position: relative; transform: translate3d(0px, 0px, 0px);">
                            <div class="sidebar-content-wrap">
                                
                                <div class="sidebar-content">
                                    <div class="sidebar-menu">
                                        <div class="sidebar-menu__title">홈</div>
                                        <ul class="sidebar-menu__list">
                                            <li class="sidebar-menu__item sidebar-menu__item--active">
                                                <a href="">
                                                    전체
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="sidebar-menu">
                                        <div class="sidebar-menu__title">커뮤니티</div>
                                        <ul class="sidebar-menu__list">
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    자유
                                                </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    투표 피드백
                                                </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                    파티 구함
                                               </a>
                                            </li>
                                            <li class="sidebar-menu__item ">
                                                <a href="">
                                                     통합 인기글
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="board-wrapper">
                    <div class="board-header">
                        <div class="sort-buttons">
                            <button class="btn-popular">인기순</button>
                            <button class="btn-latest">최신순</button>
                        </div>
                        <div class="board-search">
                          <form action="" method="GET">
                            <select name="search-option">
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                            </select>
                            <input type="text" name="search" placeholder="검색어를 입력하세요">
                            <button type="submit">검색</button>
                          </form>
                        </div>
                        <div class="board-write">
                          <a href="#">글쓰기</a>
                        </div>
                      </div>

                    <div class="board-list">
                        <div class="board-item">
                            <div class="board-title">글 제목1</div>
                            <div class="board-author">글쓴이1</div>
                            <div class="board-date">작성일1</div>
                        </div>
                        <div class="board-item">
                            <div class="board-title">글 제목2</div>
                            <div class="board-author">글쓴이2</div>
                            <div class="board-date">작성일2</div>
                        </div>
                        <div class="board-item">
                            <div class="board-title">글 제목3</div>
                            <div class="board-author">글쓴이3</div>
                            <div class="board-date">작성일3</div>
                        </div>
                        <div class="board-item">
                            <div class="board-title">글 제목4</div>
                            <div class="board-author">글쓴이4</div>
                            <div class="board-date">작성일4</div>
                        </div>
                        <div class="board-item">
                            <div class="board-title">글 제목5</div>
                            <div class="board-author">글쓴이5</div>
                            <div class="board-date">작성일5</div>
                        </div>
                        <div class="board-item">
                            <div class="board-title">글 제목6</div>
                            <div class="board-author">글쓴이6</div>
                            <div class="board-date">작성일6</div>
                        </div>
                        <!-- 추가로 글 목록을 생성하면 됩니다. -->
                        <div class="board-footer">
                            <div class="board-paging">
                              <a href="#" class="prev">이전</a>
                              <a href="#" class="next">다음</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="ADbanner right">
                    <img src="banner_right.png" alt="Banner Right">
                </div>
		    </div>
	</main>
</body>

</html>

