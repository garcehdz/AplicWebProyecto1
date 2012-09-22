<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Sistema de Tiquetes</title>
	<link href="/styles.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
  <h2>Agregar un Promotor</h2>
  
  <form method="GET" action="/tickets/domain.PromotersInsert" >
  <table>
	<tr>
		<td>Nombre:</td>
		<td><input type="text" value="" name="name"></td>
	</tr>
	
	<tr>
		<td>Direccion:</td>
		<td><input type="text" value="" name="address"></td>
	</tr>
	
	<tr>
		<td>Telefono:</td>
		<td><input type="text" value="" name="telephone"></td>
	</tr>
	
	<tr>
		<td>Numero de Cuenta:</td>
		<td><input type="text" value="" name="account"></td>
	</tr>
	
	<tr>
		<td>Banco:</td>
		<td><input type="text" value="" name="bank"></td>
	</tr>
	
	<tr>
		<td>Comision:</td>
		<td><input type="text" value="" name="comission"></td>
	</tr>
	
	<tr>
		<td>Username:</td>
		<td><input type="text" value="" name="username"></td>
	</tr>
	
	<tr>
		<td>Contrase&ntilde;a:</td>
		<td><input type="password" value="" name="pass"></td>
	</tr>
	
	<tr><td colspan="2"><input type="submit" value="Agregar"/></td></tr>

  </table>
  </form>
  </body>
</html>