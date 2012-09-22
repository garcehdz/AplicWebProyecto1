package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de eliminacion de un promotor
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromotersDelete extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Administrator"))
			{
				if (!hasEvents())
				{
					deletePromoter();
					deletePromoterUser();
				}
				else
				{
					request.setAttribute("warning", "El promotor tiene eventos");
				}
				response.sendRedirect("tickets/domain.PromotersList");
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
	
	public boolean hasEvents() throws Exception
	{
		EventFinder eventFinder = (EventFinder)context.getBean("EventFinder");
		List<EventRowGateway> events = eventFinder.findByPromoter(Integer.parseInt(request.getParameter("promoter")));
		return !events.isEmpty();
	}

	public void deletePromoter() throws Exception
	{
		UserFinder users = (UserFinder)context.getBean("UserFinder");
		UserRowGateway user = users.find(request.getParameter("user"));
		user.delete();
	}

	public void deletePromoterUser() throws Exception
	{
		PromoterFinder promoters = (PromoterFinder)context.getBean("PromoterFinder");
		PromoterRowGateway promoter = promoters.find(Integer.parseInt(request.getParameter("promoter")));
		promoter.delete();
	}
}