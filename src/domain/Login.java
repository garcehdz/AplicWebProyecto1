package domain;

import data.*;
import display.FrontCommand;
import java.util.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 * Controla el evento de ingreso y salida de usuarios 
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class Login extends FrontCommand {

	public void process() throws ServletException, IOException {
		
		String accion =  request.getParameter("accion");
		HttpSession session = request.getSession(true);

		if(accion != null && accion.equals("Login")){
			
			UserFinder users = (UserFinder) context.getBean("UserFinder");
			String username = request.getParameter("username");
			String pass = request.getParameter("pass");
			UserRowGateway user = users.find(username);
			if(user != null && user.getPass().equals(pass)){
				List<String> rolesUsuario = users.findRoles(username);
				session.setAttribute("Username", username);
				session.setAttribute("Administrator", false);
				session.setAttribute("Promoter", false);
				
				for(String s:rolesUsuario)
				{
					if(s.equals("Administrator"))
						session.setAttribute("Administrator", true);
					if(s.equals("Promoter")){
						session.setAttribute("Promoter", true);
						PromoterFinder promoters = (PromoterFinder) context.getBean("PromoterFinder");
						int promoterCode = promoters.findPromoterCodeByUsername(username);
						session.setAttribute("PromoterCode", promoterCode);
					}						
				}				
			}
			else{
				request.setAttribute("error", "Favor verifique la informacion");
				forward("/login.jsp");
			}
		}
		else{			
			session.invalidate();
			forward("/login.jsp");
		}
		forward("/paginaPrincipal.jsp");
	}
	
}