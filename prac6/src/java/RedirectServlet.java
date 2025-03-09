import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");

        // Validate URL
        if (url == null || url.trim().isEmpty()) {
            response.getWriter().println("<h3>Please enter a valid URL.</h3>");
        } else {
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url; // Ensure valid URL format
            }
            response.sendRedirect(url); // Redirect to entered URL
        }
    }
}
