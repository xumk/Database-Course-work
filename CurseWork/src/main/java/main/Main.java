package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlets.Frontend;

/**
 * Created by Алексей on 05.03.2016.
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Frontend allRequestsServlet = new Frontend();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/mirror");
        Server server = new Server(8080);
        server.setHandler(context);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error("Ошибка запуска сервера: ", e);
        }
    }
}
