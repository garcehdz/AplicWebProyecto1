package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de edicion de un promotor
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromotersUpdate extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Administrator"))
			{
				updateUser();
				updatePromoter();
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

	public void updateUser() throws Exception
	{
		UserFinder userFinder = (UserFinder)context.getBean("UserFinder");
		UserRowGateway user = userFinder.create();
		user.setUsername(request.getParameter("txtUserUserName"));
		user.setPass(request.getParameter("txtUserPass"));
		user.update();
	}

	public void updatePromoter() throws Exception
	{
		PromoterFinder promoterFinder = (PromoterFinder)context.getBean("PromoterFinder");
		PromoterRowGateway promoter = promoterFinder.create();
		promoter.setCode(Integer.parseInt(request.getParameter("txtPromoterCode")));
		promoter.setName(request.getParameter("txtPromoterName"));
		promoter.setAddress(request.getParameter("txtPromoterAddress"));
		promoter.setTelephone(request.getParameter("txtPromoterTelephone"));
		promoter.setAccount(request.getParameter("txtPromoterAccount"));
		promoter.setBank(request.getParameter("txtPromoterBank"));
		promoter.setComission(Integer.parseInt(request.getParameter("txtPromoterComission")));
		promoter.setUsername(request.getParameter("txtUserUserName"));
		promoter.update();
	}
}