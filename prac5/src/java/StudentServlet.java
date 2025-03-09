import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Store student details in session
        session.setAttribute("name", request.getParameter("name"));
        session.setAttribute("id", request.getParameter("id"));
        session.setAttribute("department", request.getParameter("department"));

        // Redirect to marks entry page
        response.sendRedirect("marks.html");
    }
}


}
