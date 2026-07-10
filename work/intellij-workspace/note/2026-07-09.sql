-- =====================================================
-- Oracle HR Schema for MariaDB
-- =====================================================
-- DROP TABLE IF EXISTS employees;
-- DROP TABLE IF EXISTS departments;
-- DROP TABLE IF EXISTS jobs;
-- =====================================================
-- JOBS

CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_no VARCHAR(50) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    balance INT NOT NULL DEFAULT 0,
    account_type VARCHAR(50) DEFAULT 'SAVINGS',
    created_at DATE DEFAULT CURRENT_DATE
) AUTO_INCREMENT = 1;



-- =====================================================
CREATE TABLE jobs (
    job_id      VARCHAR(10) NOT NULL,
    job_title   VARCHAR(100) NOT NULL,
    min_salary  INT,
    max_salary  INT,
    PRIMARY KEY(job_id)
);
-- =====================================================
-- DEPARTMENTS
-- =====================================================
CREATE TABLE departments (
    department_id   INT NOT NULL,
    department_name VARCHAR(100) NOT NULL,
    manager_id      INT,
    location_id     INT,
    PRIMARY KEY(department_id)
);
-- =====================================================
-- EMPLOYEES
-- =====================================================
CREATE TABLE employees (
    employee_id      INT NOT NULL,
    first_name       VARCHAR(50),
    last_name        VARCHAR(50) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    phone_number     VARCHAR(30),
    hire_date        DATE NOT NULL,
    job_id           VARCHAR(10) NOT NULL,
    salary           DECIMAL(10,2),
    commission_pct   DECIMAL(5,2),
    manager_id       INT,
    department_id    INT,
    PRIMARY KEY(employee_id)
);
-- =====================================================
-- INDEX
-- =====================================================
CREATE INDEX idx_emp_department
ON employees(department_id);
CREATE INDEX idx_emp_job
ON employees(job_id);
CREATE INDEX idx_emp_manager
ON employees(manager_id);
CREATE INDEX idx_dept_manager
ON departments(manager_id);
-- =====================================================
-- FOREIGN KEY
-- =====================================================
ALTER TABLE employees
ADD CONSTRAINT fk_emp_job
FOREIGN KEY(job_id)
REFERENCES jobs(job_id);
ALTER TABLE employees
ADD CONSTRAINT fk_emp_department
FOREIGN KEY(department_id)
REFERENCES departments(department_id);
ALTER TABLE employees
ADD CONSTRAINT fk_emp_manager
FOREIGN KEY(manager_id)
REFERENCES employees(employee_id);
ALTER TABLE departments
ADD CONSTRAINT fk_dept_manager
FOREIGN KEY(manager_id)
REFERENCES employees(employee_id);
INSERT INTO jobs(job_id, job_title, min_salary, max_salary) VALUES
('AD_PRES', 'President', 20000, 40000),
('AD_VP', 'Administration Vice President', 15000, 30000),
('IT_PROG', 'Programmer', 4000, 10000),
('SA_REP', 'Sales Representative', 6000, 12000),
('MK_MAN', 'Marketing Manager', 9000, 15000),
('HR_REP', 'HR Representative', 4000, 9000),
('FI_MGR', 'Finance Manager', 9000, 16000),
('FI_ACCOUNT', 'Accountant', 4000, 9000),
('PU_MAN', 'Purchasing Manager', 8000, 15000),
('ST_CLERK', 'Stock Clerk', 2500, 6000);
INSERT INTO departments(department_id, department_name, manager_id, location_id) VALUES
(10, 'Administration', NULL, 1700),
(20, 'Marketing', NULL, 1800),
(30, 'Purchasing', NULL, 1700),
(40, 'Human Resources', NULL, 2400),
(50, 'Shipping', NULL, 1500),
(60, 'IT', NULL, 1400),
(70, 'Public Relations', NULL, 2700),
(80, 'Sales', NULL, 2500),
(90, 'Executive', NULL, 1700),
(100, 'Finance', NULL, 1700);
INSERT INTO employees
(employee_id, first_name, last_name, email, phone_number,
hire_date, job_id, salary, commission_pct, manager_id, department_id)
VALUES
(100,'Steven','King','SKING','515.123.1111','2020-01-01','AD_PRES',24000,NULL,NULL,90),
(101,'Neena','Kochhar','NKOCHHAR','515.123.2222','2020-01-15','AD_VP',17000,NULL,100,90),
(102,'Lex','De Haan','LDEHAAN','515.123.3333','2020-02-01','AD_VP',17000,NULL,100,90),
(103,'Alexander','Hunold','AHUNOLD','590.423.4567','2020-03-01','IT_PROG',9000,NULL,102,60),
(104,'Bruce','Ernst','BERNST','590.423.4568','2020-03-10','IT_PROG',6000,NULL,103,60),
(105,'David','Austin','DAUSTIN','590.423.4569','2020-03-20','IT_PROG',4800,NULL,103,60),
(106,'Valli','Pataballa','VPATABAL','590.423.4570','2020-04-01','IT_PROG',4800,NULL,103,60),
(107,'Diana','Lorentz','DLORENTZ','590.423.4571','2020-04-10','IT_PROG',4200,NULL,103,60),
(108,'Nancy','Greenberg','NGREENBE','515.124.1111','2020-05-01','FI_MGR',12000,NULL,101,100),
(109,'Daniel','Faviet','DFAVIET','515.124.1112','2020-05-15','FI_ACCOUNT',9000,NULL,108,100),
(110,'John','Chen','JCHEN','515.124.1113','2020-06-01','FI_ACCOUNT',8200,NULL,108,100),
(111,'Ismael','Sciarra','ISCIARRA','515.124.1114','2020-06-10','FI_ACCOUNT',7700,NULL,108,100),
(112,'Jose','Manuel','JMANUEL','515.124.1115','2020-06-20','FI_ACCOUNT',7800,NULL,108,100),
(113,'Luis','Popp','LPOPP','515.124.1116','2020-07-01','FI_ACCOUNT',6900,NULL,108,100),
(114,'Den','Raphaely','DRAPHAEL','515.125.1111','2020-08-01','PU_MAN',11000,NULL,101,30),
(115,'Alexander','Khoo','AKHOO','515.125.1112','2020-08-15','ST_CLERK',3100,NULL,114,50),
(116,'Shelli','Baida','SBAIDA','515.125.1113','2020-09-01','ST_CLERK',2900,NULL,114,50),
(117,'Sigal','Tobias','STOBIAS','515.125.1114','2020-09-10','ST_CLERK',2800,NULL,114,50),
(118,'Guy','Himuro','GHIMURO','515.125.1115','2020-09-20','SA_REP',8000,0.15,101,80),
(119,'Karen','Colmenares','KCOLMENA','515.125.1116','2020-10-01','HR_REP',6500,NULL,101,40);