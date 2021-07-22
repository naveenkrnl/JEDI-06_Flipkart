package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.flipkart.bean.Student;
import com.flipkart.application.CRSApplication;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.business.StudentOperation;
import com.flipkart.utils.DBUtils;

/**
 * 
 * Class to implement Student Dao Operations
 *
 */
public class StudentDaoOperation implements StudentDaoInterface {
	
	private static volatile StudentDaoOperation instance=null;
	private static Logger logger = Logger.getLogger(StudentOperation.class);

	private StudentDaoOperation()
	{
		
	}

	public static StudentDaoOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(StudentDaoOperation.class){
				instance=new StudentDaoOperation();
			}
		}
		return instance;
	}

	@Override
	public int addStudent(Student student) throws StudentNotRegisteredException{
		Connection connection=DBUtils.getConnection();
		int studentId=0;
		try
		{
			//open db connection
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
			preparedStatement.setString(1, student.getUserId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getPassword());
			preparedStatement.setString(4, student.getRole().toString());
			preparedStatement.setString(5, student.getGender().toString());
			preparedStatement.setString(6, student.getAddress());
			preparedStatement.setString(7, student.getCountry());
			int rowsAffected=preparedStatement.executeUpdate();
			if(rowsAffected==1)
			{
				//add the student record
				//"insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
				PreparedStatement preparedStatementStudent;
				preparedStatementStudent=connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT,Statement.RETURN_GENERATED_KEYS);
				preparedStatementStudent.setString(1,student.getUserId());
				preparedStatementStudent.setString(2, student.getBranchName());
				preparedStatementStudent.setInt(3, student.getBatch());
				preparedStatementStudent.setBoolean(4, false);
				preparedStatementStudent.executeUpdate();
				ResultSet results=preparedStatementStudent.getGeneratedKeys();
				if(results.next())
					studentId=results.getInt(1);
			}
			
			
		}
		catch(Exception ex)
		{
			throw new StudentNotRegisteredException(student.getName());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"SQL error");
				e.printStackTrace();
			}
		}
		return studentId;
	}

	@Override
	public int getStudentId(String userId) {
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_ID);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getInt("studentId");
			}
				
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
		return 0;
	}

	@Override
	public boolean isApproved(int studentId) {
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);
			statement.setInt(1, studentId);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next())
			{
				return rs.getBoolean("isApproved");
			}
				
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		
		return false;
	}

}