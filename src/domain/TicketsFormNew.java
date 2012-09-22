package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de creacion del formulario para la insercion de tickets
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class TicketsFormNew extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			setLocations();
			setEvent();
			forward("/ticketsNew.jsp");
		}
		catch(Exception e)
		{
			request.setAttribute("error", e.getMessage());
			forward("/error.jsp");
		}
	}
	
	public void setLocations() throws Exception
	{
		HashMap param;
		List<HashMap> params = new ArrayList<HashMap>();
		
		LocationTypeFinder locationTypeFinder = (LocationTypeFinder)context.getBean("LocationTypeFinder");
		List<LocationTypeRowGateway> locationTypes = locationTypeFinder.findAll();
		
		LocationFinder locationFinder = (LocationFinder)context.getBean("LocationFinder");
		List<LocationRowGateway> locations = locationFinder.findByEventId(Integer.parseInt(request.getParameter("event")));
		
		for (LocationRowGateway location : locations)
		{
			for (LocationTypeRowGateway locationType : locationTypes)
			{
				if(location.getLocationType() == locationType.getCode())
				{
					param = new HashMap();
					param.put("code", locationType.getCode());
					param.put("description", locationType.getDescription());
					param.put("numbered", location.getNumbered());
					param.put("quantity", location.getQuantity());
					param.put("available", location.getQuantity() - getNumberOfTicketsByEventAndLocation(location.getEvent(), location.getLocationType()));
					params.add(param);
					break;
				}
			}
		}
		request.setAttribute("locations", params);
	}

	public void setEvent() throws Exception
	{
		EventFinder eventFinder = (EventFinder)context.getBean("EventFinder");
		EventRowGateway event = eventFinder.find(Integer.parseInt(request.getParameter("event")));
		Map param = new HashMap();
		param.put("code", event.getCode());
		param.put("eventType", event.getEventType());
		param.put("name", event.getName());
		param.put("artists", event.getArtists());
		param.put("date", event.getDate());
		param.put("place", event.getPlace());
		param.put("promoterId", event.getPromoter());
		request.setAttribute("event", param);
	}
	
	public int getNumberOfTicketsByEventAndLocation(int eventId, int locationId) throws Exception
	{
		int numberOfTickets = 0;
		TicketFinder ticketFinder = (TicketFinder)context.getBean("TicketFinder");
		List<TicketRowGateway> tickets = ticketFinder.findByEventAndLocation(eventId, locationId);
		for (TicketRowGateway ticket : tickets) 
		{
			numberOfTickets += ticket.getQuantity();
		}
		return numberOfTickets;
	}
}