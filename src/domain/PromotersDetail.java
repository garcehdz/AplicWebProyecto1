//Pestaña:Promotores
//Muestra el detalle de un promotor
//Permisos:Administrador

package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de mostrar el detalle de promotor
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromotersDetail extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Administrator"))
			{
				setPromoterUserDetails();
				setPromoterDetails();
				forward("/promotersDetail.jsp");
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

	public void setPromoterUserDetails() throws Exception
	{
		UserFinder users = (UserFinder)context.getBean("UserFinder");
		UserRowGateway user = users.find(request.getParameter("user"));
		Map params = new HashMap();
		params.put("username", user.getUsername());
		params.put("pass", user.getPass());
		request.setAttribute("user", params);
	}

	public void setPromoterDetails() throws Exception
	{
		PromoterFinder promoters = (PromoterFinder)context.getBean("PromoterFinder");
		PromoterRowGateway promoter = promoters.find(Integer.parseInt(request.getParameter("promoter")));
		Map params = new HashMap();
		params.put("code", promoter.getCode());
		params.put("name", promoter.getName());
		params.put("address", promoter.getAddress());
		params.put("telephone", promoter.getTelephone());
		params.put("account", promoter.getAccount());
		params.put("bank", promoter.getBank());
		params.put("comission", promoter.getComission());
		request.setAttribute("promoter", params);
	}
}