//Pesta�a:Eventos
//Ingresa un evento
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
 * Controla el evento de edicion de eventos
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventsUpdate extends FrontCommand
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
				updateEvent();
				deleteLocations();
				insertLocations();
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
		location.deleteByEvent(codigoEvento);		
	}

	public void insertLocations() throws Exception
	{
		LocationTypeFinder locationTypeFinder = (LocationTypeFinder)context.getBean("LocationTypeFinder");
		List<LocationTypeRowGateway> locationTypes = locationTypeFinder.findAll();
		
		LocationFinder locations = (LocationFinder)context.getBean("LocationFinder");
		LocationRowGateway location;
		for (LocationTypeRowGateway locationType : locationTypes)
		{
			if (request.getParameter("locationType" + locationType.getCode()) != null)
			{
				location = locations.create();
				location.setLocationType(locationType.getCode());
				location.setEvent(this.codigoEvento);
				location.setPrice(Integer.parseInt(request.getParameter("locationPrice" + locationType.getCode())));
				location.setQuantity(Integer.parseInt(request.getParameter("locationQuantity" + locationType.getCode())));
				location.setNumbered(request.getParameter("locationNumbered" + locationType.getCode()) != null);
				location.insert();
			}
		}
	}

	public void updateEvent() throws Exception
	{
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("PromoterCode") != null){
			int promoterCode = (Integer)session.getAttribute("PromoterCode");			
			EventFinder events = (EventFinder)context.getBean("EventFinder");		
			EventRowGateway event = events.find(codigoEvento);
			event.setName(request.getParameter("name"));
			event.setEventType(Integer.parseInt(request.getParameter("eventType")));
			event.setArtists(request.getParameter("artists"));
			event.setDate(request.getParameter("date"));
			event.setPlace(request.getParameter("place"));
			event.setPromoter(promoterCode);
			event.update();
		}
		else{
			request.setAttribute("error", "Session no contiene el PromoterCode");
			forward("/error.jsp");
		}
	}
}