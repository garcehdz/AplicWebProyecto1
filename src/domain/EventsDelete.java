//Pestaña:Eventos
//Elimina un evento
//Permisos:Promotor

package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de eliminacion de un evento
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventsDelete extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Promoter"))
			{
				deleteLocations();
				deleteEvent();
				response.sendRedirect("tickets/domain.EventsListByPromoter");
			}
			else
			{
				request.setAttribute("error", "Acceso denegado");
				forward("/login.jsp");
			}
		}
		catch(Exception e)
		{
			request.setAttribute("error", e.getMessage());
			forward("/error.jsp");
		}
	}

	public void deleteLocations() throws Exception
	{
		LocationFinder locations = (LocationFinder)context.getBean("LocationFinder");
		LocationRowGateway location = locations.create();
		location.deleteByEvent(Integer.parseInt(request.getParameter("code")));		
	}

	public void deleteEvent() throws Exception
	{
		EventFinder eventFinder = (EventFinder)context.getBean("EventFinder");
		EventRowGateway event = eventFinder.find(Integer.parseInt(request.getParameter("code")));
		event.delete();
	}
}