import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Servlet for inserting user data into the database
 */
@WebServlet("/Servlet1")
public class Servlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet1() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String birthdate = req.getParameter("birthdate");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
        
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");

            
            String query = "INSERT INTO info (username, email, birthdate) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, birthdate);

         
            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                resp.sendRedirect(req.getContextPath() + "/Servlet2");
            } else {
                out.println("<h3>Unable to insert data. Please try again.</h3>");
            }

        } catch (ClassNotFoundException e) {
            out.println("<h3>MySQL Driver not found! " + e.getMessage() + "</h3>");
        } catch (SQLException e) {
            out.println("<h3>Database error: " + e.getMessage() + "</h3>");
        } finally {
            
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
                if (out != null) out.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
