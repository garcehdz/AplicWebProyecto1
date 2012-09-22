<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Sistema de Tiquetes</title>
	<link href="/styles.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
	<h2>Agregar un Evento</h2>
	
	<% List eventTypes = (List)request.getAttribute("eventTypes"); %>
	<% List locations = (List)request.getAttribute("locationTypes"); %>
  
	<form method="GET" action="/tickets/domain.EventsInsert" >
	<table>
		<tr>
			<td>
				<table>
					<tr>
						<td>Nombre:</td><td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<td>Tipo Evento:</td>
						<td>
							<select name="eventType">
								<% for(int i = 0, n = eventTypes.size(); i < n; i++) {
									Map eventType = (Map)eventTypes.get(i); %>
									<option value="<%= eventType.get("code")%>"><%= eventType.get("description")%></option>
								<%}%>
							</select>
						</td>
					</tr>
					<tr>
						<td>Artistas:</td><td><input type="text" name="artists"/></td>
					</tr>
					<tr>
						<td>Fecha:</td><td><input type="text" name="date"/></td>
					</tr>
					<tr>
						<td>Lugar:</td><td><input type="text" name="place"/></td>
					</tr>
				</table>
			</td>
			
			<td>
				<table align="top">
					<tr>
						<th colspan="3">Localidades</th>
					</tr>
					
					<% for(int j = 0; j < locations.size(); j++) {
						Map location = (Map)locations.get(j); %>
						<tr>
							<td><input type="checkbox" name="locationType<%=location.get("code")%>"><%=location.get("description")%></input></td>
							<td><input type="checkbox" name="locationNumbered<%=location.get("code")%>">Numerada&nbsp;&nbsp;&nbsp;</input></td>
							<td>Precio:<input type="text" name="locationPrice<%=location.get("code")%>"/></td>
							<td>Cantidad:<input type="text" name="locationQuantity<%=location.get("code")%>"/></td>
						</tr>
					<%}%>
				</table>
			</td>
		</tr>
		
		<tr><td colspan="2"><input type="submit" value="Guardar"/></td></tr>
	</table>
	</form>
  </body>
</html>