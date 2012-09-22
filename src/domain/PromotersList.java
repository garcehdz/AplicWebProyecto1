package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de listado de promotores
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromotersList extends FrontCommand
{
	public void process() throws ServletException, IOException
	{
		try
		{
			HttpSession session = request.getSession(false);
			if ((Boolean)session.getAttribute("Administrator"))
			{
				setPromoters();
				forward("/promotersList.jsp");
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
	
	public void setPromoters() throws Exception
	{
		HashMap param;
		PromoterFinder promoterFinder = (PromoterFinder)context.getBean("PromoterFinder");
		List<PromoterRowGateway> promoters = promoterFinder.findAll();
		List<HashMap> params = new ArrayList<HashMap>();
		for (PromoterRowGateway promoter : promoters)
		{
			param = new HashMap();
			param.put("code", promoter.getCode());
			param.put("name", promoter.getName());
			param.put("address", promoter.getAddress());
			param.put("telephone", promoter.getTelephone());
			param.put("account", promoter.getAccount());
			param.put("bank", promoter.getBank());
			param.put("comission", promoter.getComission());
			param.put("username", promoter.getUsername());
			params.add(param);
		}
		request.setAttribute("promoters", params);
	}
}