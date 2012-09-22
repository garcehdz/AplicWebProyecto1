//Pestaña: eventos
//pasa los datos necesarios a el formulario de ingreso de eventos
//Permisos: Promotor

package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de mostrar el detalle de un evento
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventsDetail extends FrontCommand
{
	public int codigoEvento;
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Promoter"))
			{
				codigoEvento = Integer.parseInt(request.getParameter("code"));
				setEventTypes();
				setLocationTypes();
				setEvent();
				setLocationsEvent();
				setTicketsEvent();
				forward("/eventsDetail.jsp");
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
	
	public void setTicketsEvent() throws Exception
	{
		TicketFinder tickets = (TicketFinder)context.getBean("TicketFinder");
		List<TicketRowGateway> ticketsEvent = tickets.findByEvent(codigoEvento);
		
		List<Map> params = new ArrayList();
		Map param;
		for (TicketRowGateway ticket : ticketsEvent)
		{
			param = new HashMap();
			param.put("code", ticket.getCode());
			param.put("type", ticket.getLocationType());
			param.put("date", ticket.getDate());
			param.put("amount", ticket.getAmount());
			params.add(param);
		}
		request.setAttribute("ticketsEvent", params);
	}
	
	public void setLocationsEvent() throws Exception
	{
		LocationFinder locations = (LocationFinder)context.getBean("LocationFinder");
		List<LocationRowGateway> locationsEvent = locations.findByEventId(codigoEvento);

		List<Map> params = new ArrayList();
		Map param;
		for (LocationRowGateway location : locationsEvent)
		{
			param = new HashMap();
			param.put("type", location.getLocationType());
			param.put("price", location.getPrice());
			param.put("quantity", location.getQuantity());
			param.put("numbered", location.getNumbered());
			params.add(param);
		}
		request.setAttribute("locationsEvent", params);
	}
	
	public void setEvent() throws Exception
	{
		EventFinder events = (EventFinder)context.getBean("EventFinder");
		EventRowGateway event = events.find(Integer.parseInt(request.getParameter("code")));
		Map param = new HashMap();
		param.put("code", event.getCode());
		param.put("eventType", event.getEventType());
		param.put("name", event.getName());
		param.put("artists", event.getArtists());
		param.put("date", event.getDate());
		param.put("place", event.getPlace());
		request.setAttribute("event", param);
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
	
	public void setLocationTypes() throws Exception
	{
		Map param;
		LocationTypeFinder locationTypes = (LocationTypeFinder)context.getBean("LocationTypeFinder");
		List<LocationTypeRowGateway> locationTypesList = locationTypes.findAll();
		List<Map> params = new ArrayList();
		for (LocationTypeRowGateway locationType : locationTypesList)
		{
			param = new HashMap();
			param.put("code", locationType.getCode());
			param.put("description", locationType.getDescription());
			params.add(param);
		}
		request.setAttribute("locationTypes", params);
	}
}