package main;

import controllers.MyFirstServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by araragi on 7/31/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        MyFirstServlet myFirstServlet = new MyFirstServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(myFirstServlet), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }



}
