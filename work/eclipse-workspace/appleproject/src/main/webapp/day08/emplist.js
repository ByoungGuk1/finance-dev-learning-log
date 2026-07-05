const cpath = "/appleproject";

$(() => {
    $("#btnAll").on("click", btnAll);
    $("#btnNew").on("click", showInsertForm);
    $("#btnCancel").on("click", hideInsertForm);
    $("#btnSave").on("click", saveEmp);
    //이벤트 위임 => js: closeset()
    $("#empBody").on("click", ".btnEdit", showDetail);
    $("#empBody").on("click", ".btnDel", delEmp);

    $("#btnSearch").on("click", searchEmp);
    $("#detail-find-btn").on("click", searchDetail);

    //이벤트 호출
    $("#btnAll").click();
    showDept();
    showJobs();
});

async function searchDetail() {
    const condition = getDetailCondition();
    const foundEmpList = await findDetailEmpList(condition);
    console.log(foundEmpList);
    renderData(foundEmpList);
}

function getDetailCondition() {
    return {
        "department-id": $("#select-dept-id").val(),
        "job-id": $("#select-job-id").val(),
        "salary": $("#select-salary").val() || 0,
        "hire-date": $("#select-hire-date").val()
    };
}

async function showJobs() {
    const jobList = await getJobList();
    renderSelectJob(jobList);
}

async function showDept() {
    const deptList = await getDeptList();
    renderSelectDept(deptList);
}

async function searchEmp() {
    const inputEmpName = $("#searchName").val();
    const foundEmp = await getEmpByName(inputEmpName);
    renderDetail(foundEmp);
}

async function delEmp() {
    const empId = $(this).data("empid");
    await deleteEmp(empId).then(({ result }) => {
        if (result == null) {
            alert("삭제 실패");
            return;
        }
        alert(`${result}건 삭제 완료`);
        $("#btnAll").click();
    });
}

async function saveEmp() {
    const empObj = getEmpFormData();
    console.log(empObj);
    await postEmpDetail(empObj).then(({ result }) => {
        if (result == null) {
            alert("저장 실패");
            return;
        }
        $("#btnAll").click();
        $("#formBox").hide();
    });
}

function getEmpFormData() {
    /*
    const emp1 = $("#myfrm").serialize(); // get 방식일 때 => employee_id=100&first_name=kim...
    console.log(emp1);
    */
    const emp2 = $("#myfrm").serializeArray(); // [{},{},...]
    console.log(emp2);
    let emp3 = {};
    $.each(emp2, (idx, data) => {
        emp3[data.name] = data.value;
    });
    emp3 = {
        ...emp3,
        phoneNumber: "",
        commissionPct: 0,
        managerId: 0
    };

    const empObj = {
        firstName: $("#first_name").val(),
        lastName: $("#last_name").val(),
        jobId: $("#job_id").val(),
        email: $("#email").val(),
        departmentId: $("#department_id").val(),
        salary: $("#salary").val() || 0,
        hireDate: $("#hire_date").val(),
        phoneNumber: "",
        commissionPct: 0,
        managerId: 0
    };

    return empObj;
}

async function showDetail() {
    const empId = $(this).data("empid");
    const empData = await getEmpDetail(empId);
    renderDetail(empData);
}

function hideInsertForm() {
    $("#formBox").hide();
}
function showInsertForm() {
    $("#employee_id").attr("data-empid", "");
    $("#employee_id").val("");
    $("#first_name").val("");
    $("#last_name").val("");
    $("#job_id").val("");
    $("#email").val("");
    $("#department_id").val("");
    $("#salary").val("");
    $("#hire_date").val("");
    $("#formBox").show();
}

async function btnAll() {
    const data = await getEmpList();
    renderData(data);
}

async function getJobList() {
    return await $.ajax({
        url: `${cpath}/api/emp/job-list.do`,
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}

async function getDeptList() {
    return await $.ajax({
        url: `${cpath}/api/emp/dept-list.do`,
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}

//default 경로 : http://192.168.0.24:9999
//contextPath : /appleproject
async function getEmpList() {
    return await $.ajax({
        url: `${cpath}/api/emp/list.do`,
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}
async function getEmpDetail(empid) {
    return await $.ajax({
        url: `${cpath}/api/emp/detail.do`,
        data: { "emp-id": empid, "first-name": null },
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}

async function getEmpByName(inputEmpName) {
    return await $.ajax({
        url: `${cpath}/api/emp/detail.do`,
        data: { "emp-id": null, "first-name": inputEmpName },
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}

async function findDetailEmpList(condition) {
    return await $.ajax({
        url: `${cpath}/api/emp/find.do`,
        data: condition,
        type: "post",
        dataType: "json",
        success: (data) => data
    });
}

async function deleteEmp(empid) {
    return await $.ajax({
        url: `${cpath}/api/emp/delete.do`,
        data: { "emp-id": empid },
        type: "get",
        dataType: "json",
        success: (data) => data
    });
}

async function postEmpDetail(empObj) {
    const empId = $("#employee_id").attr("data-empid");
    let uri = "";
    let emp = empObj;
    if (empId) {
        uri = "detail.do";
        emp = {
            employeeId: Number(empId),
            ...empObj,
        }
    } else {
        uri = "insert.do";
        emp = {
            employeeId: Number($("#employee_id").val()),
            ...empObj
        }
    }
    return await $.ajax({
        url: `${cpath}/api/emp/${uri}`,
        data: emp,
        type: "post",
        dataType: "json",
        success: (data) => data
    });
}

function renderSelectDept(deptList) {
    $("#select-dept-id").empty();
    $("#select-dept-id").append(`<option value="">전체</option>`);
    $.each(deptList, (index, dept) => {
        $("#select-dept-id").append(`
			<option value="${dept.departmentId}">${dept.departmentName}</option>
			`);
    });
}

function renderSelectJob(jobList) {
    $("#select-job-id").empty();
    $("#select-job-id").append(`<option value="">전체</option>`);
    $.each(jobList, (index, job) => {
        $("#select-job-id").append(`
		<option value="${job.jobId}">${job.jobTitle}</option>
		`);
    });
}

function renderDetail(empData) {
    $("#employee_id").val(empData.employeeId);
    $("#employee_id").attr("data-empid", empData.employeeId);
    $("#first_name").val(empData.firstName);
    $("#last_name").val(empData.lastName);
    $("#job_id").val(empData.jobId);
    $("#email").val(empData.email);
    $("#department_id").val(empData.departmentId);
    $("#salary").val(empData.salary);
    $("#hire_date").val(formatDate(empData.hireDate));
    $("#formBox").show();
}

function formatDate(dateStr) {
    const nums = dateStr.match(/(\d+)/g);
    const year = nums[2];
    const month = nums[0].padStart(2, "0");
    const day = nums[1].padStart(2, "0");
    return `${year}-${month}-${day}`
}

function renderData(empList) {
    const tbody = $("#empBody");
    tbody.empty();
    if (empList.length == 0) {
        tbody.append("<tr><td colspan = '8'>조회한 데이터가 없습니다.</td></tr>");
        return;
    }
    $.each(empList, (index, emp) => {
        const empRow = `
			<tr>
				<td>${emp.employeeId}</td>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>
				<td>${emp.jobId}</td>
				<td>${emp.departmentId}</td>
				<td>${emp.salary.toLocaleString()}</td>
				<td>
					<button class="btnEdit" data-empid="${emp.employeeId}">detail</button>
					<button class="btnDel" data-empid="${emp.employeeId}">del</button>
				</td>
			</tr>
			`;
        tbody.append(empRow);
    });
}