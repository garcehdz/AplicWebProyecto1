<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Sistema de Tiquetes</title>
	<link href="/styles.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
  <h2>Detalle de Promotor</h2>
  
  <form method="GET" action="/tickets/domain.PromotersUpdate" >
  <% Map prom = (Map)request.getAttribute("promoter"); %>
  <% Map user = (Map)request.getAttribute("user"); %>
  <table>
	<input type="hidden" name="txtPromoterCode" value="<%= prom.get("code")%>"/>
	<tr>
		<td>Nombre:</td>
		<td><input type="text" value="<%= prom.get("name")%>" name="txtPromoterName"></td>
	</tr>
				
	<tr>
		<td>Direccion:</td>
		<td><input type="text" value="<%= prom.get("address")%>" name="txtPromoterAddress"></td>
	</tr>
				
	<tr>
		<td>Telefono:</td>
		<td><input type="text" value="<%= prom.get("telephone")%>" name="txtPromoterTelephone"></td>
	</tr>
		
	<tr>
		<td>Numero de Cuenta:</td>
		<td><input type="text" value="<%= prom.get("account")%>" name="txtPromoterAccount"></td>
	</tr>
				
	<tr>
		<td>Banco:</td>
		<td><input type="text" value="<%= prom.get("bank")%>" name="txtPromoterBank"></td>
	</tr>
				
	<tr>
		<td>Comision:</td>
		<td><input type="text" value="<%= prom.get("comission")%>" name="txtPromoterComission"></td>
	</tr>
			
	<tr>
		<td>Username:</td>
		<td><input type="text" value="<%= user.get("username")%>" name="txtUserUserName"></td>
	</tr>
				
	<tr>
		<td>Contrase&ntilde;a:</td>
		<td><input type="password" value="<%= user.get("pass")%>" name="txtUserPass"></td>
	</tr>
				
	<tr><td colspan="2"><input type="submit" value="Actualizar"/></td></tr>

  </table>
  </form>
  </body>
</html>