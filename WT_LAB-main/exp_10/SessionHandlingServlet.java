import java.io.*; 
import javax.servlet.*; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.*; 
 
// Map the servlet to the URL using @WebServlet annotation 
@WebServlet("/SessionHandlingServlet") 
public class SessionHandlingServlet extends HttpServlet { 
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
IOException { 
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 
 
 
        // Get username from the form or default to Guest 
        String username = request.getParameter("username"); 
        if (username == null || username.isEmpty()) { 
            username = "Guest"; 
        } 
 
        // Session Handling Methods 
 
        // 1. URL Rewriting 
        String urlRewritingLink = "SessionHandlingServlet?username=" + username; 
 
        // 2. Hidden Form Field 
        String hiddenForm = "<form action='SessionHandlingServlet' method='post'>" 
                + "<input type='hidden' name='username' value='" + username + "'>" 
                + "<button type='submit'>Submit with Hidden Field</button></form>"; 
 
        // 3. Cookies 
        Cookie userCookie = new Cookie("username", username); 
        userCookie.setMaxAge(3600); // Expires in 1 hour 
        response.addCookie(userCookie); 
 
        // 4. HTTP Session 
        HttpSession session = request.getSession(); 
        session.setAttribute("username", username); 
 
        // Response Output 
        out.println("<html><body>"); 
        out.println("<h1>Welcome, " + username + "</h1>"); 
        out.println("<h2>Session Handling Demonstration</h2>"); 
        out.println("<ul>"); 
        out.println("<li><a href='" + urlRewritingLink + "'>URL Rewriting</a></li>"); 
        out.println("<li>" + hiddenForm + "</li>"); 
        out.println("<li>Cookie Set: " + userCookie.getValue() + "</li>"); 
        out.println("</ul>"); 
        out.println("<h3>Session Details:</h3>"); 
        out.println("<p>Session ID: " + session.getId() + "</p>"); 
        out.println("<p>Username in session: " + session.getAttribute("username") + "</p>"); 
        out.println("</body></html>"); 
    } 
 
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws 
IOException { 
        doPost(request, response); 
    } 
} 