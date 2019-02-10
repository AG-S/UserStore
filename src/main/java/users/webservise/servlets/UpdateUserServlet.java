package users.webservise.servlets;

import users.entity.User;
import users.service.impl.UserService;
import users.webservise.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UpdateUserServlet extends HttpServlet {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        User user = userService.findById(id);
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String,Object> params = new HashMap<>();
        params.put("user",user);
        String page = pageGenerator.getPage("index_update.html",params);
        resp.getWriter().write(page);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setId(Integer.valueOf(req.getParameter("id")));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setSalary(Double.valueOf(req.getParameter("salary")));
        user.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
        userService.update(user);
        resp.sendRedirect("/users");

    }
}
