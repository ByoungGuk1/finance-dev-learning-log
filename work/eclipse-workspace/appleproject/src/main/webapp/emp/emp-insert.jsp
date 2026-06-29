<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert</title>
<link rel="styleSheet" href="../resources/css/emp-style.css" />
</head>
<body>
	<a href="list.do">목록으로 이동</a>
	<h1>신규 직원 추가</h1>
	<br />
	<form action="insert.do" method="post">
		<div>
			<label for="emp-id">직원번호 : </label><input type="number" id="emp-id"
				autofocus="autofocus" name="employeeId" required="required"
				placeholder="필수 입력" />
		</div>
		<div>
			<label for="first-name">이름 : </label><input type="text"
				id="first-name" name="firstName" />
		</div>
		<div>
			<label for="last-name">성 : </label><input type="text" id="last-name"
				name="lastName" required="required" placeholder="필수 입력" />
		</div>
		<div>
			<label for="email">이메일 : </label><input type="text" id="email"
				name="email" required="required" placeholder="필수 입력" />
		</div>
		<div>
			<label for="phone-number">전화번호 : </label><input type="text"
				id="phone-number" name="phoneNumber" />
		</div>
		<div>
			<label for="hire-date">입사일 : </label><input type="date"
				id="hire-date" name="hireDate" required="required"
				placeholder="필수 입력" />
		</div>
		<div>
			<label for="job-id">jobId : </label><input type="text" id="job-id"
				name="jobId" required="required" placeholder="필수 입력" />
		</div>
		<div>
			<label>급여 : </label><input type="number" name="salary" />
		</div>
		<div>
			<label>커미션 : </label><input type="number" name="commissionPct"
				step="0.01" />
		</div>
		<div>
			<label>메니저 : </label><input type="number" name="managerId" />
		</div>
		<div>
			<label for="departmentId">부서번호 : </label> <select name="departmentId">
				<option value="">부서 없음</option>
				<c:forEach var="dept" items="${deptlist}">
					<option value="${dept.departmentId}">${dept.departmentName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="submit-wrapper">
			<input type="submit" value="서버전송" /> <input type="reset"
				value="초기화" />
		</div>
	</form>
</body>
</html>