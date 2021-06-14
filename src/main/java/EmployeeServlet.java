import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostgresConnection postgresConnection;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        postgresConnection = new PostgresConnection();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Connection connection = null;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWritter = response.getWriter();
		try {
			connection = postgresConnection.getConnection();
			String nameParam = request.getParameter("name");
			if(nameParam == null || nameParam == ""){			
				throw new NullPointerException("The parameter \"name\" can't be empty.");
			}
			PreparedStatement statements = connection
					.prepareStatement("SELECT * FROM employees WHERE \"Name\" = ?");			
			statements.setString(1, nameParam.trim());
			ResultSet resultSet = statements.executeQuery();
			while(resultSet.next()) {
				Employee result = new Employee();
				result.setId(resultSet.getInt("Id"));
				result.setName(resultSet.getString("Name"));
				result.setPosition(resultSet.getString("Position"));
				printWritter.print(result.toString() + "<br>");
			}
		}
		catch (NullPointerException e) {
			printWritter.write(e.getMessage());
		}
		catch(SQLException e) {
			printWritter.write("Database error: <br>" + e.getMessage());
		}
		catch(Exception e) {
			printWritter.write(e.getClass() + "<br>" + e.getMessage());
		}
		
	}
}
