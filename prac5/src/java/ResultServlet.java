import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Fetch marks from session
        int[] marks = (int[]) session.getAttribute("marks");

        // Calculate total & determine pass/fail
        int total = 0;
        boolean pass = true;
        for (int mark : marks) {
            total += mark;
            if (mark < 40) {
                pass = false;
            }
        }
        String result = pass ? "Pass" : "Fail";
        session.setAttribute("result", result);

        // Forward to result.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
}
