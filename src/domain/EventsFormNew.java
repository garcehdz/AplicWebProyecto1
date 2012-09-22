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
 * Controla el evento de creacion del formulario para el ingreso de un nuevo evento
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventsFormNew extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Promoter"))
			{
				setEventTypes();
				setLocationTypes();
				forward("/eventsNew.jsp");
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