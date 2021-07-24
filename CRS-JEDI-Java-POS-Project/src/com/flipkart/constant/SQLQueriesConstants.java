package com.flipkart.constant;

/**
 *
 * @author JEDI-03
 *
 */

public class SQLQueriesConstants {

    // AdminDao Queries
    public static final String ADD_ADMIN_QUERY = "insert into admin(userId) values(?)";
    public static final String APPROVE_STUDENT_QUERY = "update student set isApproved = 1 where userId = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select user.name, user.gender, user.address, user.country, user.userId, user.role, user.password, user.email, user.doj, professor.department, professor.designation from professor inner join user on user.userId = professor.userId";
    public static final String VIEW_PENDING_ADMISSION_QUERY = "select user.userId, user.name, user.email, user.password, user.role, user.gender, user.address, user.country, user.doj, student.branchName, student.batch, student.rollNumber from student join user on (user.userId = student.userId) where student.isApproved = 0";
    public static final String ADD_COURSE_QUERY = "insert into Course(courseName, courseCatalogId) values (?, ?)";
    public static final String ADD_COURSE_QUERY_WITH_PROFID = "insert into Course(courseName, courseCatalogId , professorUserId) values (?, ?, ?)";
    public static final String ASSIGN_COURSE_QUERY = "update Course set professorUserId = ? where courseCode = ?";
    public static final String DELETE_REGISTERED_COURSE_FROM_COURSE_ID = "delete from RegisteredCourse where courseCode = ?";
    public static final String DELETE_COURSE_QUERY = "delete from Course where courseCode = ?";
    public static final String GET_COURSE_LIST_FROM_COURSE_CATALOG_ID = "select courseCode, courseName, professorUserId, courseCatalogId from Course where courseCatalogId = ?";

    // Professsor Queries
    public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, department, designation) values (?, ?, ?)";
    public static final String GET_PROFESSOR_FROM_USERID = "select userId, department, designation from professor where userId = ? ";
    public static final String VERIFY_CREDENTIALS = "select password from user where userId = ?";
    public static final String GET_ROLE = "select role from user where userId = ? ";
    public static final String IS_APPROVED = "select isApproved from student where userId = ? ";
    public static final String GET_STUDENT_ID = "select userId from user where email = ? ";
    public static final String GET_PROF_NAME = "select name from user where userId = ?";

    // Student Queries
    public static final String ADD_STUDENT = "insert into student (userId,branchName,batch,rollNumber,isApproved) values (?,?,?,?,?)";
    public static final String GET_STUDENT_FROM_USERID = "select userId,branchName,batch,rollNumber,isApproved from student where userId = ?";

    public static final String VIEW_REGISTERED_COURSES = " select * from course inner join enrolled_courses on course.courseCode = enrolled_courses.courseCode where enrolled_courses.studentId = ?";
    public static final String VIEW_AVAILABLE_COURSES = " select * from course where courseCode not in  (select courseCode  from enrolled_courses where studentId = ?) and course.isOffered = ? and seats > 0";
    public static final String CHECK_COURSE_AVAILABILITY = " select courseCode from enrolled_courses where studentId = ? ";
    public static final String DECREMENT_COURSE_SEATS = "update course set seats = seats-1 where courseCode = ? ";
    public static final String ADD_COURSE = "insert into enrolled_courses (studentId,courseCode) values ( ? , ? )";
    public static final String DROP_COURSE_QUERY = "delete from enrolled_courses where courseCode = ? AND studentId = ?;";
    public static final String INCREMENT_SEAT_QUERY = "update course set seats = seats + 1 where  courseCode = ?;";
    public static final String CALCULATE_FEES = "select sum(courseFee) from course where courseCode in (select courseCode from enrolled_courses where studentId = ?);";
    public static final String VIEW_GRADE = "select course.courseCode,course.courseName,enrolled_courses.grade from course inner join enrolled_courses on course.courseCode = enrolled_courses.courseCode where enrolled_courses.studentId = ?;";
    public static final String GET_SEATS = "select seats from course where courseCode = ?;";
    public static final String INSERT_PAYMENT = "insert into payment(studentId,modeofPayment,referenceId,amount) values(?,?,?,?);";
    public static final String INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
    public static final String GET_NOTIFICATION = "select * from notification where referenceId = ?;";
    public static final String ADD_GRADE = "update enrolled_course set grade=? where courseCode=? and studentId=?";
    public static final String GET_COURSES = "select * from course where professorId=?";
    public static final String GET_REGISTRATION_STATUS = " select isRegistered from student where studentId = ? ";
    public static final String SET_REGISTRATION_STATUS = "update student set isRegistered = true  where studentId=?";
    public static final String GET_ENROLLED_STUDENTS = "select course.courseCode,course.courseName,enrolled_courses.studentId from course inner join enrolled_courses on course.courseCode = enrolled_courses.courseCode where course.professorId = ? order by course.courseCode";
    public static final String NUMBER_OF_REGISTERED_COURSES = "  select enrolled_course.courseCode,course.courseName, enrolled_course.studentId From enrolled_course inner join course on enrolled_course.courseCode = course.courseCode where course.professorId = ? order by course.courseCode";
    public static final String IS_REGISTERED = " select courseCode from enrolled_courses where courseCode=? and studentId=? ";

    // User Queries
    public static final String DELETE_USER_FROM_USER_ID = "delete from user where userId = ?";
    public static final String GET_USER_INFO_FROM_USERID = "select userId, name, email, password, role, gender, address, country, doj from user where userId = ? ";
    public static final String GET_USER_INFO_FROM_EMAIL = "select userId, name, email, password, role, gender, address, country, doj from user where email = ? ";
    public static final String ADD_USER_QUERY = "insert into User(name, gender, address, country, role, password, email) values (?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PASSWORD = "update user set password=? where email = ? ";

}