package users;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import users.dao.jdbc.JdbcUserDao;
import users.service.impl.UserService;
import users.webservise.servlets.*;

public class Starter {

        public static void main(String[] args) throws Exception {

            JdbcUserDao jdbcUserDao = new JdbcUserDao();
            UserService userService = new UserService();
            userService.setUserDao(jdbcUserDao);

            GetAllUserServlet getAllUserServlet = new GetAllUserServlet();
            getAllUserServlet.setUserService(userService);

            AddUserServlet addUserServlet = new AddUserServlet();
            addUserServlet.setUserService(userService);

            UpdateUserServlet updateUserServlet = new UpdateUserServlet();
            updateUserServlet.setUserService(userService);

            DeleteUserServlet deleteUserServlet = new DeleteUserServlet();
            deleteUserServlet.setUserService(userService);

            LoginServlet loginServlet = new LoginServlet();

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(getAllUserServlet), "/users");
            context.addServlet(new ServletHolder(addUserServlet), "/users/add");
            context.addServlet(new ServletHolder(updateUserServlet), "/users/update");
            context.addServlet(new ServletHolder(deleteUserServlet), "/users/delete");
            context.addServlet(new ServletHolder(loginServlet),"/login");

            Server server = new Server(8080);
            server.setHandler(context);

            server.start();
        }

}
