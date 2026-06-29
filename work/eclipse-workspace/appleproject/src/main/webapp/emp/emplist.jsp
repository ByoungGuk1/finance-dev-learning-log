<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- core 라서 주로 c 사용 --%>
<%-- jsp 주석 --%>
<%-- taglib는 라이브러리가 있어야 사용 가능 (WEB-INF/lib/*.jar) --%>
<%-- EL은 조건문, 반복문이 없음 --%>
<%-- JSTL 사용 : 조건문과 반복문이 있음 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empList</title>
<link rel="stylesheet" href="../resources/css/external.css">
<style>
/* 2. Internal StyleSheet : 내부 문서에 style 작성 */
h1 {
	border: 3px dotted #f00;
	text-align: center;
}

table {
	border-collapse: collapse; /* 셀 테두리 합치기 (실무 필수) */
	width: 100%;
}

th, td {
	border: 1px solid #ddd;
	padding: 12px 16px;
	text-align: left;
}

th {
	background-color: #2E75B6;
	color: white;
	font-weight: 700;
}

tr:nth-child(even) {
	background-color: #f5f8ff;
} /* 홀짝 줄무늬 */
caption {
	caption-side: bottom;
	font-size: 13px;
	color: #888;
}

.delete-button {
	border-radius: 25px;
	border: 1px solid black;
	background-color: transparent;
}

td:nth-child(3)[data-value*="in"] {
	color: red;
}

td:nth-child(5)[data-value*="123"] {
	color: green;
}

td:nth-child(6)[data-value$="01"] {
	color: blue;
}
</style>
</head>
<body>
	<!-- 1. inline style sheet -->
	<h1 style="background-color: #0ff;">모든 직원의 목록</h1>
	<!-- checkbox 특징 : 서로 독립적 : 나의 선택이 다른 선택에 영향을 주지 않음 -->
	<!-- radio 특징  : 서로 베타적 : 선택이 일어나면 다른 선택에 영향을 준다 -->
	<label for="select-emp">직원 선택</label>
	<input id="select-emp" type="checkbox" name="checkbox" value="1" />
	<fieldset>
		<legend>베타적</legend>
		<label>직원</label> <input type="radio" name="radio${emp.employeeId}"
			value="직원" /> <label>임원</label> <input type="radio"
			name="radio${emp.employeeId}" value="임원" />
	</fieldset>
	<a href="insert.do">신규 직원 등록</a>
	<table border="1">
		<caption>모든 직원의 목록</caption>
		<thead>
			<tr>
				<th>직원번호</th>
				<th>fname</th>
				<th>lname</th>
				<th>email</th>
				<th>phone</th>
				<th>hiredate</th>
				<th>job</th>
				<th>salary</th>
				<th>comm</th>
				<th>manager</th>
				<th>dept</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${emplist}">
				<tr>
					<td><a href="detail.do?emp-id=${emp.employeeId}">${emp.employeeId}</a></td>
					<td data-value="${emp.firstName}">${emp.firstName}</td>
					<td data-value="${emp.lastName}">${emp.lastName}</td>
					<td data-value="${emp.email}">${emp.email}</td>
					<td data-value="${emp.phoneNumber}">${emp.phoneNumber}</td>
					<td data-value="${emp.hireDate}">${emp.hireDate}</td>
					<td data-value="${emp.jobId}">${emp.jobId}</td>
					<td data-value="${emp.salary}">${emp.salary}</td>
					<td data-value="${emp.commissionPct}">${emp.commissionPct}</td>
					<td data-value="${emp.managerId}">${emp.managerId}</td>
					<td data-value="${emp.departmentId}">${emp.departmentId}</td>
					<td><button id="${emp.employeeId}" class="delete-button"
							onclick="location.href='delete.do?employee-id=${emp.employeeId}'">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="11">hr계정의 Employees Datas</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>