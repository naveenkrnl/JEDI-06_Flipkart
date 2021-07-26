package com.flipkart.dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.UUID;

// import com.flipkart.constant.ModeOfPayment;
// import com.flipkart.constant.NotificationType;
// import com.flipkart.constant.SQLQueriesConstants;
// import com.flipkart.business.NotificationOperation;
// import com.flipkart.utils.DBUtils;

/**
 * 
 * Class to implement Notification Dao Operations Used for adding the
 * notification to the database
 *
 */
public class NotificationDaoOperation implements NotificationDaoInterface {
	// private static volatile NotificationDaoOperation instance=null;

	// private NotificationDaoOperation()
	// {

	// }

	// public static NotificationDaoOperation getInstance()
	// {
	// if(instance==null)
	// {
	// // This is a synchronized block, when multiple threads will access this
	// instance
	// synchronized(NotificationDaoOperation.class){
	// instance=new NotificationDaoOperation();
	// }
	// }
	// return instance;
	// }

	// @Override
	// public int sendNotification(NotificationType type, int
	// studentId,ModeOfPayment modeOfPayment,double amount) throws SQLException{
	// int notificationId=0;
	// Connection connection=DBUtils.getConnection();
	// try
	// {
	// //INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId)
	// values(?,?,?);";
	// PreparedStatement ps =
	// connection.prepareStatement(SQLQueriesConstants.INSERT_NOTIFICATION,Statement.RETURN_GENERATED_KEYS);
	// ps.setInt(1, studentId);
	// ps.setString(2,type.toString());
	// if(type==NotificationType.PAYMENT)
	// {
	// //insert into payment, get reference id and add here
	// UUID referenceId=addPayment(studentId, modeOfPayment,amount);
	// ps.setString(3, referenceId.toString());
	// System.out.println("Message - "); System.out.println("Payment successful,
	// Reference ID: "+referenceId);
	// }
	// else
	// ps.setString(3,"");

	// ps.executeUpdate();
	// ResultSet results=ps.getGeneratedKeys();
	// if(results.next())
	// notificationId=results.getInt(1);

	// switch(type)
	// {
	// case REGISTRATION:
	// System.out.println("Message - "); System.out.println("Registration
	// successful. Administration will verify the details and approve it!");
	// break;
	// case REGISTRATION_APPROVAL:
	// System.out.println("Message - "); System.out.println("Student with id
	// "+studentId+" has been approved!");
	// break;
	// case PAYMENT:
	// System.out.println("Message - "); System.out.println("Student with id
	// "+studentId+" fee has been paid");
	// }

	// }
	// catch(SQLException ex)
	// {
	// throw ex;
	// }
	// return notificationId;
	// }

	// public UUID addPayment(int studentId, ModeOfPayment modeOfPayment,double
	// amount) throws SQLException
	// {
	// UUID referenceId;
	// Connection connection=DBUtils.getConnection();
	// try
	// {
	// referenceId=UUID.randomUUID();
	// //INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId)
	// values(?,?,?);";
	// PreparedStatement statement =
	// connection.prepareStatement(SQLQueriesConstants.INSERT_PAYMENT);
	// statement.setInt(1, studentId);
	// statement.setString(2, modeOfPayment.toString());
	// statement.setString(3,referenceId.toString());
	// statement.setDouble(4, amount);
	// statement.executeUpdate();
	// //check if record is added
	// }
	// catch(SQLException ex)
	// {
	// throw ex;
	// }
	// return referenceId;
	// }

}