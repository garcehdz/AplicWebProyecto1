package display;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.context.*;
import org.springframework.web.context.support.*;

/**
 * Clase que sirve como enrutador de comandos
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class FrontController extends HttpServlet {

  private WebApplicationContext context;

  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
  }

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
    throws ServletException,IOException {
    FrontCommand command =
       getCommand((String)request.getAttribute("command"));
    command.init(context,request,response);
    command.process();
  }

  private FrontCommand getCommand(String commandName) {
    try {
      return (FrontCommand) getCommandClass(commandName).newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private Class getCommandClass(String commandName) {
    Class result;
    try {
      result = Class.forName(commandName);
    } catch (ClassNotFoundException e) {
      result = UnknownCommand.class;
    }
    return result;
  }
}