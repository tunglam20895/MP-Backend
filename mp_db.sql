--Procject table
create table projects(
    id NUMBER PRIMARY KEY,
    name NVARCHAR2(100) not null,
    description VARCHAR2(500),
    date_started DATE,
    date_end DATE,
    status NUMBER
)

--Division table
create table divisions(
    id NUMBER PRIMARY KEY,
    name NVARCHAR2(100),
    manager_id  NUMBER CONSTRAINT manager_id REFERENCES users(id),
    description NVARCHAR2(500)
)

--User table
create table users(
    id NUMBER PRIMARY KEY,
    division_id NUMBER CONSTRAINT division_id REFERENCES divisions(id),
    role NVARCHAR2(50) NOT NULL,
    username NVARCHAR2(100) NOT NULL,
    password NVARCHAR2(200) NOT NULL,
    email NVARCHAR2(100) NOT NULL,
    phone NUMBER,
    avartar NVARCHAR2(1000),
    first_name NVARCHAR2(50),
    middle_name NVARCHAR2(50),
    last_name NVARCHAR2(50),
    type NUMBER,
    personal_id NUMBER,
    home_town_ NVARCHAR2(100),
    education NVARCHAR2(100),
    school NVARCHAR2(100),
    major NVARCHAR2(100),
    status NUMBER,
    day_off_last_year NUMBER,
    date_created DATE
    )
--Divison_project table
create table divison_project(
    id NUMBER NOT NULL PRIMARY KEY,
    divisionId NUMBER CONSTRAINT divisionId REFERENCES divisions(id),
    project_id NUMBER CONSTRAINT project_id REFERENCES projects(id)
)

--Project_user table
create table project_user(
    id NUMBER PRIMARY KEY NOT NULL,
    projectID NUMBER CONSTRAINT projectID REFERENCES projects(id),
    user_id NUMBER CONSTRAINT user_id REFERENCES users(id),
    isPM int
)

--Report table
create table reports(
    id NUMBER PRIMARY KEY,
    project_IDD NUMBER CONSTRAINT project_IDD REFERENCES projects(id),
    userID NUMBER CONSTRAINT userID REFERENCES users(id),
    advantage NVARCHAR2(200),
    disadvantage NVARCHAR2(200),
    dificulty NVARCHAR2(200),
    propose NVARCHAR2(200),
    date_created DATE,
    date_read DATE
)

--Leave_requests table
create table leave_requests(
    id NUMBER PRIMARY KEY NOT NULL,
    user_requested NUMBER CONSTRAINT user_requested REFERENCES users(id) ,
    user_approved  NUMBER CONSTRAINT user_approved REFERENCES users(id) ,
    date_created DATE,
    date_approved DATE,
    status NUMBER,
    type NUMBER CONSTRAINT type REFERENCES request_type(id),
    date_requested DATE,
    duration NUMBER
)



--Reaquests_type table
create table request_type(
    id NUMBER PRIMARY KEY NOT NULL,
    name NVARCHAR2(100),
    unit NVARCHAR2(20)
)

--file table
create table files(
    id NUMBER PRIMARY KEY NOT NULL,
    name NVARCHAR2(100),
    path NVARCHAR2(500),
    uploader NUMBER CONSTRAINT uploader REFERENCES users(id)
)

--file-project table
create table file_project(
    id NUMBER PRIMARY KEY NOT NULL,
    file_id NUMBER CONSTRAINT file_id REFERENCES files(id),
    projects_id NUMBER CONSTRAINT projects_id REFERENCES projects(id),
    category NVARCHAR2(100)
)

--issues table
create table issues(
id NUMBER PRIMARY KEY NOT NULL,
projectsID NUMBER CONSTRAINT projectsID REFERENCES projects(id),
user_created NUMBER CONSTRAINT user_created REFERENCES users(id),
assignee NUMBER CONSTRAINT assignee REFERENCES users(id),
status NUMBER,
parent_issue NUMBER CONSTRAINT parent_issue REFERENCES issues(id)
)

--alter table issues add 
--parent_issue NUMBER CONSTRAINT parent_issue REFERENCES issues(id)

--file_issue table
create table file_issue(
    id NUMBER PRIMARY KEY NOT NULL,
    fileID NUMBER CONSTRAINT fileID REFERENCES files(id),
    issueID NUMBER CONSTRAINT issueID REFERENCES issues(id)  
)

--time_log table
create table time_log(
    id NUMBER PRIMARY KEY NOT NULL,
    issue_id NUMBER CONSTRAINT issue_id REFERENCES issues(id),
    users_id NUMBER CONSTRAINT users_id REFERENCES users(id),
    date_log DATE,
    time_log NUMBER   
)

--issue change log table
create table issue_change_log(
    id NUMBER PRIMARY KEY NOT NULL,
    issues_id NUMBER CONSTRAINT issues_id REFERENCES issues(id),
    user_modified  NUMBER CONSTRAINT user_modified REFERENCES users(id),
    title NVARCHAR2(100),
    description NVARCHAR2(500),
    change_date DATE,
    start_date DATE,
    due_date DATE,
    target NUMBER,
    priority NUMBER,
    process NUMBER,
    solution NVARCHAR2(2000),
    time_spend NUMBER,
    status NUMBER,
    comments NVARCHAR2(2000)
)

drop table issue_change_log
