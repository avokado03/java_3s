import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresConnection {
	private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/servlets";
    private static final String USER = "postgres";
    private static final String PASS = "12345";
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
    	try {
    		Class.forName("org.postgresql.Drive");
    		Connection connection = DriverManager
    				.getConnection(DB_URL, USER, PASS);
        	return connection;
    	}
    	catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			throw new ClassNotFoundException("Postgre driver not found");
    	}
    	catch(SQLException e){
    		System.out.println("Can't connect to db");
    		throw e;
    	}
    }  
}
