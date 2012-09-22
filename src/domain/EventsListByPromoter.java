//Pestaña:Eventos
//Lista todos los eventos asociados a un promotor
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
 * Controla el evento de listado de eventos por promotor
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventsListByPromoter extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Promoter"))
			{
				setEventTypes();
				setEvents();
				forward("/eventsListByPromoter.jsp");
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

	public void setEventTypes() throws Exception
	{
		Map param;
		EventTypeFinder eventTypes = (EventTypeFinder)context.getBean("EventTypeFinder");
		List<EventTypeRowGateway> eventTypeList = eventTypes.findAll();
		List<Map> params = new ArrayList();
		for (EventTypeRowGateway eventType : eventTypeList)
		{
			param = new HashMap();
			param.put("code", eventType.getCode());
			param.put("description", eventType.getDescription());
			params.add(param);
		}
		request.setAttribute("eventTypes", params);
	}
	
	public void setEvents() throws Exception
	{
		
		Map param;
		EventFinder eventFinder = (EventFinder)context.getBean("EventFinder");
		List<EventRowGateway> events = eventFinder.findByPromoter((Integer)request.getSession(false).getAttribute("PromoterCode"));
		List<Map> params = new ArrayList();
		for (EventRowGateway event : events)
		{
			param = new HashMap();
			param.put("code", event.getCode());
			param.put("name", event.getName());
			param.put("eventType", event.getEventType());
			param.put("artists", event.getArtists());
			param.put("date", event.getDate());
			param.put("place", event.getPlace());
			param.put("promoter", event.getPromoter());
			params.add(param);
		}
		request.setAttribute("events", params);
	}
}