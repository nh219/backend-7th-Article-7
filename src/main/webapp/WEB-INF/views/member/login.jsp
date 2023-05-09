<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Teamluck.gg</title>
	<link rel="stylesheet" href="../WebContent/login style.css">
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
					<li><a href="<c:url value='/member/login' />">로그인</a></li>
					<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
				</ul>
			</nav>
		</div>
	</header>

	<!-- 메인 영역 -->
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
				<div class="member-card-layout__container">
					<div class="member-card-layout__inner">
						<h1 class="member-card-layout__logo">
							<img class="member-card-layout__logo-image" src="" alt="Teamluck.gg"></h1>
						<div class="login">
							<form>
								<h2 class="login__email-title">이메일 로그인</h2>
								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput8450" class="member-input__box" type="text" autocomplete="off" name="email" value="">
										<label for="memberInput8450" class="member-input__label">이메일 주소</label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>
								<div class="member-input">
									<div class="member-input__state">
										<input id="memberInput304" class="member-input__box" type="password" autocomplete="off" name="password" value="">
										<label for="memberInput304" class="member-input__label">비밀번호</label>
										<span class="member-input__valid-wrapper"></span>
									</div>
								</div>
								<button type="submit" class="member-button login__btn">로그인</button>
								</button>
								<div class="login__l-sign-up">Teamluck.gg에 처음이세요?
									<span class="login__sign-up-link">
										<a class="member-link" href="/register/age?idType=OPGG">회원가입하기</a>
									</span>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="ADbanner right">
					<img src="banner_right.png" alt="Banner Right">
				</div>
			</div>
		</div>
	</main>
</body>
</html>