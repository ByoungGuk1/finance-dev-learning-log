<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
</head>
<body>
	<audio src="../resources/media/spring.mp3" controls="controls"></audio>
	<br />
	<a href="https://www.naver.com" target="_blank">네이버</a>
	<br />
	<c:if test="${emp.managerId != 0}">
		<a href="detail.do?emp-id=${emp.managerId}" target="_self">해당 직원의
			관리자 상세보기</a>
		<br />
	</c:if>
	<a href="list.do">목록으로 이동</a>
	<br />
	<video src="/appleproject/resources/media/flower.mp4" autoplay="autoplay" loop="loop" controls="controls"></video>

	<h1>직원의 상세보기</h1>
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

</body>
</html>