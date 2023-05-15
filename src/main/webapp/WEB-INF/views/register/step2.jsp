<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="oracle.jdbc.driver.OracleDriver"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8" />
	<title>회원가입: Step2</title>
	<link rel="stylesheet" href="../WebContent/join style.css">
	<%-- 
	<style>
		label {
			display: inline-block;
			width: 120px;
		}
	</style>
	--%>
	<script>
	function submitHandler() {
		var userName = document.querySelector('#nickname').value;
		var password = document.querySelector('#password');
		var confirmPassword = document.querySelector('#confirmPassword');
		// 뒤에 value를 붙여주면 사용자가 입력한 값이 됨.
		
		if(userName.length >= 2 && password.value.length >= 4
				&& password.value == confirmPassword.value) {
			alert("회원가입 처리를 수행합니다.");
		}
		else {
			alert("올바른 회원 정보를 입력하세요.");
			
			password.value = '';
			confirmPassword.value = '';
			
			return false;
		}
		
		// 원래 이벤트 처리 --> 해당 이벤트에 해당하는 요청을 서버에 보내는데
		// 이때 false를 반환하면 이벤트 처리를 한 후 일어나는 것들을 수행하지 않음.
	}
	
	function init() {
		var registerForm = document.querySelector('#registerForm');
		registerForm.onsubmit = submitHandler;
	}
	
	window.onload = init;
	</script>
</head>

<body>
	<!-- 헤더 영역 -->
	<header>
		<c:url var="mainUrl" value="/main" />
		<div class="logo">
		    <a href="${mainUrl}"><img src="../WebContent/lol-logo.png" alt="Teamluck.gg"></a>
		</div>
		
		<div class="navi">
			<nav>
				<ul>
					<li><a href="<c:url value='/post/lolSearch' />">전적검색</a></li>
					<li><a href="<c:url value='/post/community' />">커뮤니티</a></li>
					<li><a href="<c:url value='/post/postCrawl' />">통합 인기글</a></li>
					
					<c:if test="${auth == null }">
					<li><a href="<c:url value='/member/login' />">로그인</a></li>
					<li><a href="<c:url value='/register/step1' />">회원가입</a></li>
					</c:if>
					
					<c:if test="${auth != null }">
					${auth.nickname}<li><a href="<c:url value='/member/logout' />">로그아웃</a></li>
					</c:if>
				</ul>
			</nav>
		</div>
	</header>

	<!-- 메인 영역 -->
	<main>
		<div class="main section">
			
			<!-- 대문짝 -->
			<div class="main banner">
				<img src="../WebContent/teamluckgglogo2.png" alt="Teamluck.gg">
			</div>

			<!-- 하단 영역 -->
			<div class="ADbanner">

				<!-- 좌측 배너 -->
				<div class="ADbanner left">
				    <a href="https://lafudgestore.com/" target="_blank">
				        <img src="../WebContent/banner1.PNG" alt="Banner Left">
				    </a>
				</div>

				<!-- 콘텐츠 영역 -->
				<div class="member-scroll-layout">
                    <div class="member-scroll-layout__bg"></div>
                    <div class="member-scroll-layout__inner">
                        <div class="register-header">
                            <h1 class="register-header__logo">
                                <img class="register-header__logo-image" src="" alt="Teamluck.gg">
                            </h1>
                        </div>
                        <div class="agree">
	                    <form:form action="step2" modelAttribute="formData" id="registerForm" method="post">
                                <div class="sign-up">
                                    <h2 class="sign-up__title">기본정보입력</h2>
                                    <div class="member-input">
                                        <div class="member-input__state">
                                            <input id="memberInput1001" class="member-input__box" type="text" autocomplete="off" name="email" value="" placeholder="이메일">
                                            <span class="member-input__valid-wrapper"></span>
                                        </div>
                                    </div>
                                    <div class="member-input">
                                        <div class="member-input__state">
                                            <input id="memberInput642" class="member-input__box" type="text" autocomplete="off" name="nickname" value="" placeholder="닉네임">
                                            <span class="member-input__valid-wrapper"></span>
                                        </div>
                                    </div>
                                    <div class="member-input">
       										<div class="member-input__state">
           											<input id="memberInput8894" class="member-input__box" type="password" autocomplete="off" name="password" value="" placeholder="비밀번호">
           											<span class="member-input__valid-wrapper"></span>
       										</div>
    									</div>
   									  <div class="member-input">
       										<div class="member-input__state">
          										 		<input id="memberInput8894" class="member-input__box" type="password" autocomplete="off" name="confirmPassword" value="" placeholder="비밀번호 확인">
           											<span class="member-input__valid-wrapper"></span>
       										</div>
    								</div>
        								<div class="member-input__state">
           									 <c:if test="${not empty msg}">
               										 <p style="color: red">${msg}</p>
           									 </c:if>
        								</div>
        								
                                    <div class="sign-up__l-btn">
                                        <a href="/main"><button type="submit" class="member-button sign-up__btn" >가입하기</button></a>
                                    </div>
                                </div>
                           </form:form>
	                      </div>  
                      </div>
                	</div>
                	
					<!-- 우측 배너 -->
				<div class="ADbanner right">
				    <a href="https://fifaonline4.nexon.com/main/index" target="_blank">
				        <img src="../WebContent/banner2.PNG" alt="Banner Right">
				    </a>
				</div>
				</div>
			</div>
		</main>
	<script>
		let data = '${msg}';
		if(data!=null && data !=""){
			alert(data);
		}
	</script>
</body>
</html>