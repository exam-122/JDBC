import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MarksServlet")
public class MarksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Store marks in session
        int marks[] = new int[6];
        for (int i = 0; i < 6; i++) {
            marks[i] = Integer.parseInt(request.getParameter("subject" + (i + 1)));
        }
        session.setAttribute("marks", marks);

        // Redirect to result page
        response.sendRedirect("ResultServlet");
    }
}
