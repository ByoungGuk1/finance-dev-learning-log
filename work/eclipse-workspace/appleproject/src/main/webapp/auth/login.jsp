<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
	<!-- action: 서버 URL, method: GET/POST -->
	<form action="login.do" method="POST">
		<fieldset>
			<legend>회원 로그인</legend>

				<label for="userId">아이디</label>
				<input type="text" id="userId"
				name="user_id" placeholder="아이디를 입력하세요" required autofocus />
				
				<label for="pwd">비밀번호</label>
			  <input type="password" id="pwd" name="user_pw"
				placeholder="비밀번호 입력" required />
			
			<button type="submit">로그인</button>
			<button type="reset">초기화</button>
		</fieldset>
	</form>

</body>
</html>