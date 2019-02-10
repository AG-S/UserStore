package users.webservise.servlets;

import users.entity.User;
import users.service.impl.UserService;
import users.webservise.security.Autorization;
import users.webservise.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUserServlet extends HttpServlet {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Autorization.ifTokenExists(req.getCookies())){
            resp.sendRedirect("/login");
        } else {
            List<User> users = userService.getAll();
            PageGenerator pageGenerator = PageGenerator.instance();
            Map<String, Object> params = new HashMap<>();
            params.put("users", users);
            String page = pageGenerator.getPage("index_add.html", params);
            resp.getWriter().write(page);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(req.getParameter("Id")));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setSalary(Double.valueOf(req.getParameter("salary")));
        user.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
        userService.insert(user);
        resp.sendRedirect("/users");

    }
}
