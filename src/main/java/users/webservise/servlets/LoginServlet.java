package users.webservise.servlets;

import users.webservise.security.Autorization;
import users.webservise.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        resp.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String token = UUID.randomUUID().toString();
        Autorization.addToken(token);
        Cookie cookie = new Cookie("superuser", token);
        resp.addCookie(cookie);
        resp.sendRedirect("/users");
    }
}
