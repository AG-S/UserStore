package users;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import users.dao.jdbc.JdbcUserDao;
import users.service.impl.UserService;
import users.webservise.servlets.GetAllUserServlet;

public class Starter {

        public static void main(String[] args) throws Exception {

            JdbcUserDao jdbcUserDao = new JdbcUserDao();
            UserService userService = new UserService();
            userService.setUserDao(jdbcUserDao);

            GetAllUserServlet getAllUserServlet = new GetAllUserServlet();
            getAllUserServlet.setUserService(userService);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(getAllUserServlet), "/users");

            Server server = new Server(8080);
            server.setHandler(context);

            server.start();
        }

}
