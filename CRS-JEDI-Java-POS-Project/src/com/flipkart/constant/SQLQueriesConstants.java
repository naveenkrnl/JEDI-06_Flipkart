package com.flipkart.constant;

/**
 *
 *
 */

public class SQLQueriesConstants {
    private SQLQueriesConstants() {

    }

    // AdminDao Queries
    public static final String ADD_ADMIN_QUERY = "insert into admin(userId) values(?)";
    public static final String APPROVE_STUDENT_QUERY = "update student set isApproved = 1 where userId = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select user.name, user.gender, user.address, user.country, user.userId, user.role, user.password, user.email, user.doj, professor.department, professor.designation from professor inner join user on user.userId = professor.userId";
    public static final String VIEW_PENDING_ADMISSION_QUERY = "select user.userId, user.name, user.email, user.password, user.role, user.gender, user.address, user.country, user.doj, student.branchName, student.batch, student.rollNumber from student join user on (user.userId = student.userId) where student.isApproved = 0";
    public static final String ADD_COURSE_QUERY = "insert into Course(courseCode, courseName, courseCatalogId, courseFee) values (?, ?, ?, ?)";
    public static final String ADD_COURSE_QUERY_WITH_PROFESSOR_USER_ID = "insert into Course(courseCode, courseName, courseCatalogId, courseFee, professorUserId) values (?, ?, ?, ?)";
    public static final String GET_COURSE_FROM_COURSE_CODE_AND_CATALOG_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee from Course where courseCode = ? and courseCatalogId = ?";
    public static final String GET_COURSE_FROM_COURSE_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee from Course where courseId = ?";
    public static final String ASSIGN_COURSE_TO_PROFESSOR_FROM_COURSE_ID = "update Course set professorUserId = ? where courseId = ?";
    public static final String DELETE_REGISTERED_COURSE_FROM_COURSE_ID = "delete from RegisteredCourse where courseId = ?";
    public static final String DELETE_COURSE_FROM_FROM_COURSE_ID = "delete from Course where courseId = ?";
    public static final String GET_COURSE_LIST_FROM_COURSE_CATALOG_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee from Course where courseCatalogId = ?";

    // Professor Queries
    public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, department, designation) values (?, ?, ?)";
    public static final String GET_PROFESSOR_FROM_USERID = "select userId, department, designation from professor where userId = ? ";
    public static final String VERIFY_CREDENTIALS = "select password from user where userId = ?";
    public static final String GET_ROLE = "select role from user where userId = ? ";
    public static final String IS_APPROVED = "select isApproved from student where userId = ? ";
    public static final String GET_STUDENT_ID = "select userId from user where email = ? ";
    public static final String GET_PROF_NAME = "select name from user where userId = ?";
    public static final String GET_COURSES_BY_PROFESSOR_USER_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId from course where professorUserId = ?";
    public static final String ADD_GRADE_FROM_STUDENT_ID_AND_COURSE_ID = "update registeredCourse set grade = ? where courseId = ? and studentUserId = ?";
    public static final String GET_ALL_REGISTERED_COURSE_IDS_FROM_PROFESSOR_ID = "select registeredCourseId, studentUserId, courseId, grade from registeredCourse where registeredCourse.courseId in (select courseId from course where professorUserId = ?) ";

    // Student Queries
    public static final String ADD_STUDENT = "insert into student (userId,branchName,batch,rollNumber,isApproved) values (?,?,?,?,?)";
    public static final String GET_STUDENT_FROM_USERID = "select userId,branchName,batch,rollNumber,isApproved from student where userId = ?";
    public static final String ADD_REGISTERED_COURSE = "insert into registeredCourse(studentUserId, courseId, grade) values(?, ?, ?)";
    public static final String GET_REGISTERED_COURSE_FROM_STUDENT_ID_AND_COURSE_ID = "select registeredCourseId, studentUserId, courseId, grade from registeredCourse where studentUserId = ? and courseId = ?";
    public static final String GET_REGISTERED_COURSE_FROM_REGISTERED_COURSE_ID = "select registeredCourseId, studentUserId, courseId, grade from registeredCourse where registeredCourseId = ? ";
    public static final String NUMBER_OF_REGISTERED_COURSES_FROM_STUDENT_USER_ID = "select count(*) from registeredCourse where studentUserId = ?";
    public static final String NUMBER_OF_STUDENTS_REGISTERED_FROM_COURSE_ID = "select count(*) from registeredCourse where courseId = ?";
    public static final String IS_STUDENT_ALREADY_REGISTERED_TO_COURSE_ID = " select count(*) from registeredCourse where courseId = ? and studentUserId = ? ";
    public static final String DROP_COURSE_FROM_COURSE_ID_AND_STUDENT_ID = "delete from registeredCourse where courseId = ? AND studentUserId = ?";
    public static final String CALCULATE_FEES_FROM_STUDENT_ID = "select sum(courseFee) from course where courseId in (select courseId from registeredCourse where studentUserId = ?) and ";
    public static final String GET_REGISTERED_COURSES_FROM_STUDENT_USER_ID = "select registeredCourseId, studentUserId, courseId, grade from registeredCourse where studentUserId = ?";
    public static final String GET_COURSES_AVAILABLE_TO_STUDENT_USER_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee from course where (courseId not in (select courseId from registeredCourse where studentUserId = ?)) and ((select count(*) from registeredCourse where courseId = course.courseId) < 10)";
    public static final String GET_ALL_COURSES_REGISTERED_BY_STUDENT_USER_ID = "select courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee from course where courseId in (select courseId from registeredCourse where studentUserId = ?)";

    public static final String VIEW_REGISTERED_COURSES = " select * from course inner join registeredCourse on course.courseCode = registeredCourse.courseCode where registeredCourse.studentUserId = ?";
    public static final String CHECK_COURSE_AVAILABILITY = " select courseCode from registeredCourse where studentUserId = ? ";
    public static final String DECREMENT_COURSE_SEATS = "update course set seats = seats-1 where courseCode = ? ";
    public static final String ADD_COURSE = "insert into registeredCourse (studentUserId,courseCode) values ( ? , ? )";
    public static final String INCREMENT_SEAT_QUERY = "update course set seats = seats + 1 where  courseCode = ?;";
    public static final String VIEW_GRADE = "select course.courseCode,course.courseName,registeredCourse.grade from course inner join registeredCourse on course.courseCode = registeredCourse.courseCode where registeredCourse.studentUserId = ?;";
    public static final String INSERT_PAYMENT = "insert into payment(studentUserId,modeOfPayment,referenceId,amount) values(?,?,?,?);";
    public static final String INSERT_NOTIFICATION = "insert into notification(studentUserId,type,referenceId) values(?,?,?);";
    public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";
    public static final String GET_REGISTRATION_STATUS = " select isRegistered from student where studentUserId = ? ";
    public static final String SET_REGISTRATION_STATUS = "update student set isRegistered = true  where studentUserId=?";
    public static final String IS_REGISTERED = " select courseCode from registeredCourse where courseCode=? and studentUserId=? ";

    // User Queries
    public static final String DELETE_USER_FROM_USER_ID = "delete from user where userId = ?";
    public static final String GET_USER_INFO_FROM_USERID = "select userId, name, email, password, role, gender, address, country, doj from user where userId = ? ";
    public static final String GET_USER_INFO_FROM_EMAIL = "select userId, name, email, password, role, gender, address, country, doj from user where email = ? ";
    public static final String ADD_USER_QUERY = "insert into User(name, gender, address, country, role, password, email) values (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PASSWORD = "update user set password=? where email = ? ";

    /*
     * Table creation Quieries
     * 
     * 
     * 
     * CREATE TABLE User (
     * 
     * userId int NOT NULL AUTO_INCREMENT,
     * 
     * name varchar(255) NOT NULL,
     * 
     * email varchar(255) NOT NULL UNIQUE,
     * 
     * password varchar(255) NOT NULL, role ENUM('STUDENT', 'PROFESSOR','ADMIN') NOT
     * NULL ,
     * 
     * gender ENUM('MALE', 'FEMALE','OTHER') NOT NULL ,
     * 
     * address varchar(255) NOT NULL ,
     * 
     * country varchar(255) DEFAULT 'INDIA' NOT NULL ,
     * 
     * doj DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
     * 
     * PRIMARY KEY (userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE Student (
     * 
     * userId int NOT NULL,
     * 
     * branchName varchar(255) NOT NULL,
     * 
     * batch int NOT NULL,
     * 
     * rollNumber varchar(255) NOT NULL,
     * 
     * isApproved bool NOT NULL,
     * 
     * PRIMARY KEY (userId),
     * 
     * FOREIGN KEY (userId) REFERENCES user(userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE Professor (
     * 
     * userId int NOT NULL,
     * 
     * department varchar(255) NOT NULL,
     * 
     * designation varchar(255) NOT NULL,
     * 
     * PRIMARY KEY (userId),
     * 
     * FOREIGN KEY (userId) REFERENCES user(userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE Admin (
     * 
     * userId int NOT NULL,
     * 
     * PRIMARY KEY (userId),
     * 
     * FOREIGN KEY (userId) REFERENCES user(userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE Payment (
     * 
     * id int NOT NULL AUTO_INCREMENT,
     * 
     * studentUserId int NOT NULL,
     * 
     * referenceId varchar(255) NOT NULL,
     * 
     * amount int NOT NULL,
     * 
     * dateOfPayment DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ,
     * 
     * modeOfPayment ENUM('CREDIT_CARD','NET_BANKING','DEBIT_CARD') NOT NULL ,
     * 
     * PRIMARY KEY (id), FOREIGN KEY (studentUserId) REFERENCES student(userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE Notification (
     * 
     * id int NOT NULL AUTO_INCREMENT,
     * 
     * paymentId int, message varchar(255),
     * 
     * timestamp DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL , PRIMARY KEY (id),
     * 
     * FOREIGN KEY (paymentId) REFERENCES Payment (id)
     * 
     * );
     * 
     * 
     * CREATE TABLE Course (
     * 
     * courseId int NOT NULL AUTO_INCREMENT,
     * 
     * courseCode varchar(255) NOT NULL,
     * 
     * courseName varchar(255) NOT NULL,
     * 
     * courseFee double NOT NULL,
     * 
     * professorUserId int,
     * 
     * courseCatalogId int NOT NULL,
     * 
     * PRIMARY KEY (courseID),
     * 
     * UNIQUE INDEX (courseCode,courseCatalogId),
     * 
     * FOREIGN KEY (professorUserId) REFERENCES professor(userId)
     * 
     * );
     * 
     * 
     * 
     * CREATE TABLE RegisteredCourse(
     * 
     * registeredCourseId int NOT NULL AUTO_INCREMENT,
     * 
     * studentUserId int NOT NULL,
     * 
     * courseID int NOT NULL ,
     * 
     * grade ENUM('A', 'B', 'C', 'D', 'E', 'F', 'NA', 'EX') DEFAULT 'NA',
     * 
     * PRIMARY KEY (registeredCourseId),
     * 
     * UNIQUE INDEX(studentUserId,courseID),
     * 
     * FOREIGN KEY (studentUserId) REFERENCES student(userId),
     * 
     * FOREIGN KEY (courseID) REFERENCES course(courseID)
     * 
     * );
     */

}