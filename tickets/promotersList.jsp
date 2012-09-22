<%@ page import="java.util.*" %>

<html>
	<head>
		<title>Sistema de Tiquetes</title>
		<link href="/styles.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	<h2>Listado de Promotores</h2>
	<table width="75%">
		<tr>
			<th>Nombre</th><th>Tel&eacute;fono</th><th>Direcci&oacute;n</th><th>Comision</th>
		</tr>
		<tr>
			<% for (Map prom : (List<Map>)request.getAttribute("promoters")) { %>
			<td><%=prom.get("name")%></td>
			<td><%=prom.get("telephone")%></td>
			<td><%=prom.get("address")%></td>
			<td><%=prom.get("comission")%></td>
			<td>
				<a href='/tickets/domain.PromotersDetail?promoter=<%=prom.get("code")%>&user=<%=prom.get("username")%>'>
					<input type="submit" value="Detalle"/>
				</a>
				<a href='/tickets/domain.PromotersDelete?promoter=<%=prom.get("code")%>&user=<%=prom.get("username")%>'>
					<input type="submit" value="Eliminar"/>
				</a>
			</td>
		</tr>
		<% } %>
	</table>
	<a href='../newPromoter.jsp'>
		<input type="submit" name="action" value="Agregar"/>
	</a>
	</body>
</html>