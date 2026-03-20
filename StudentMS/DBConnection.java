package StudentMS;
import java.sql.*;

public class DBConnection {
    private static final String URL  = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASS = "Mysqlpog@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}