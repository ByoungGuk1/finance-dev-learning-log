<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
<link rel="styleSheet" href="../resources/css/emp-style.css" />
</head>
<body>
	<c:if test="${emp.managerId != 0}">
		<a href="detail.do?emp-id=${emp.managerId}" target="_self">해당 직원의
			관리자 상세보기</a>
		<br />
	</c:if>
	<a href="list.do">목록으로 이동</a>
	<br />
	<h1>상세정보</h1>
	<form action="detail.do" method="post">
		<div>
			<!-- readonly, disabled, hidden -->
			<label for="emp-id">직원번호 : </label><input type="number"
				readonly="readonly" id="emp-id" name="employeeId"
				value="${emp.employeeId}" />
		</div>
		<div>
			<label for="first-name">이름 : </label><input type="text"
				autofocus="autofocus" id="first-name" name="firstName"
				value="${emp.firstName}" />
		</div>
		<div>
			<label for="last-name">성 : </label><input type="text" id="last-name"
				name="lastName" value="${emp.lastName}" />
		</div>
		<div>
			<label for="email">이메일 : </label><input type="text" id="email"
				name="email" value="${emp.email}" />
		</div>
		<div>
			<label for="phone-number">전화번호 : </label><input type="text"
				id="phone-number" name="phoneNumber" value="${emp.phoneNumber}" />
		</div>
		<div>
			<label for="hire-date">입사일 : </label><input type="date"
				id="hire-date" name="hireDate" value="${emp.hireDate}" />
		</div>
		<div>
			<label for="job-id">jobId : </label><input type="text" id="job-id"
				name="jobId" value="${emp.jobId}" />
		</div>
		<div>
			<label>급여 : </label><input type="number" name="salary"
				value="${emp.salary}" />
		</div>
		<div>
			<label>커미션 : </label><input type="number" name="commissionPct"
				value="${emp.commissionPct}" step="0.01" />
		</div>
		<div>
			<label>메니저 : </label><input type="number" name="managerId"
				value="${emp.managerId}" />
		</div>
		<div>
			<label>부서번호 : </label><input type="number" name="departmentId"
				value="${emp.departmentId}" />
		</div>
		<div class="submit-wrapper">
			<input type="submit" value="서버전송(수정)" /> <input type="reset"
				value="초기화" />
		</div>
		<!-- <input type="button" value="JS호출" onclick="call()" />
		<button>서버전송2</button> -->
		<!-- <input type="submit" value="서버전송(수정)" />와 같은 동작 -->
	</form>
	<!-- 	<button>서버전송아님</button> -->
	<h2>직원의 상세보기</h2>
	<ul>
		<li>이름 : ${emp.firstName}</li>
		<li>성 : ${emp.lastName}</li>
		<li>이메일 : ${emp.email}</li>
		<li>전화번호 : ${emp.phoneNumber}</li>
		<li>입사일 : ${emp.hireDate}</li>
		<li>job-id : ${emp.jobId}</li>
		<li>급여 : ${emp.salary}</li>
		<li>comm : ${emp.commissionPct}</li>
		<li>관리자 id : ${emp.managerId}</li>
		<li>부서 id : ${emp.departmentId}</li>
	</ul>
	<audio src="../resources/media/spring.mp3" controls="controls"></audio>
	<br />
	<a href="https://www.naver.com" target="_blank">네이버</a>
	<br />

	<video src="/appleproject/resources/media/flower.mp4"
		autoplay="autoplay" loop="loop" controls="controls"></video>


	<!-- html 주석 (html 파서가 해석 안함) -->
	<!-- form은 submit을 누르면 input을 가지고 action의 해당하는 method로 전송 -->
	<!-- 
		get : 브라우저 주소창에서 입력됐을 때, a태그로 넘겼을 때
		post : form method = "post", JS에서 post로 보내기 => 보내고자 하는 데이터를 숨길 때 사용
		
		block 요소 : div, p
		inline 요소 : img, input, label
	 -->

</body>
<script type="text/javascript">
	function call() {
		alert("form 안의 input 타입이 button");
	}
</script>
</html>