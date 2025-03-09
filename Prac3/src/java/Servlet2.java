import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet for displaying user data in a table format
 */
@WebServlet("/Servlet2")
public class Servlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Servlet2() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

         
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");

        
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM info");

            
            ResultSetMetaData rsmt = rs.getMetaData();

            
            out.println("<html><head><title>User Data</title></head><body>");
            out.println("<h2 align='center'>User Information</h2>");
            out.println("<table align='center' border='2' cellpadding='10' cellspacing='0'>");

         
            out.println("<tr>");
            for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                out.println("<th>" + rsmt.getColumnName(i).toUpperCase() + "</th>");
            }
            out.println("</tr>");

          
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    out.println("<td>" + rs.getString(i) + "</td>");
                }
                out.println("</tr>");
            }

        
            out.println("</table>");
            out.println("</body></html>");

        } catch (ClassNotFoundException e) {
            out.println("<h3>Error: MySQL Driver not found - " + e.getMessage() + "</h3>");
        } catch (SQLException e) {
            out.println("<h3>Database error: " + e.getMessage() + "</h3>");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
                if (out != null) out.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
