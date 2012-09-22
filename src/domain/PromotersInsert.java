//Pestaña:Promotores
//Ingresa un promotor
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
 * Controla el evento de Insercion de promotores
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromotersInsert extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Administrator"))
			{
				insertUser();
				insertPromoter();
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

	public void insertUser() throws Exception
	{
		UserFinder users = (UserFinder)context.getBean("UserFinder");
		UserRowGateway user = users.create();
		user.setUsername(request.getParameter("username"));
		user.setPass(request.getParameter("pass"));
		user.insert();
		user.insertRole(2);
	}

	public void insertPromoter() throws Exception
	{
		PromoterFinder proms = (PromoterFinder)context.getBean("PromoterFinder");
		PromoterRowGateway promoter = proms.create();
		Random generator = new Random();
		promoter.setCode(generator.nextInt());
		promoter.setName(request.getParameter("name"));
		promoter.setAddress(request.getParameter("address"));
		promoter.setTelephone(request.getParameter("telephone"));
		promoter.setAccount(request.getParameter("account"));
		promoter.setBank(request.getParameter("bank"));
		promoter.setComission(Integer.parseInt(request.getParameter("comission")));
		promoter.setUsername(request.getParameter("username"));
		promoter.insert();
	}
}