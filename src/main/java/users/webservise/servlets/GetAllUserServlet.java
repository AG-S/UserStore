package users.webservise.servlets;

import users.entity.User;
import users.service.impl.UserService;
import users.webservise.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllUserServlet extends HttpServlet {



    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        Map<String,Object> params = new HashMap<>();
        params.put("users",users);
        String page = pageGenerator.getPage("index.html",params);
        resp.getWriter().write(page);
    }
}
