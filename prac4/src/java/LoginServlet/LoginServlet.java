import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/student";
    private static final String JDBC_USER = "root"; 
    private static final String JDBC_PASS = ""; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println(password.getClass().getName());
        
        if (authenticateUser(username, password)) {
            out.println("<h2>Login Successful! Welcome, " + username + ".</h2>");
        } else {
            out.println("<h2>Invalid Username or Password!</h2>");
        }

        
        out.println("<h3>Request Headers:</h3>");
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String headerName = requestHeaderNames.nextElement();
            out.println(headerName + ": " + request.getHeader(headerName) + "<br>");
        }

      
        out.println("<h3>Response Headers:</h3>");
        response.setHeader("Custom-Header", "ServletApp");
        out.println("Custom-Header: ServletApp<br>");
    }

    private boolean authenticateUser(String username, String password) {
        boolean isValidUser = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
            String sql = "SELECT * FROM auth WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            isValidUser = rs.next();
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  isValidUser;
    }
}
