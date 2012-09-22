<%@ page import="java.util.*" %>
<html>
  <head>
    <title>Sistema de Tiquetes</title>
	<link href="/styles.css" rel="stylesheet" type="text/css"/>
  </head>
	<% List eventTypes = (List)request.getAttribute("eventTypes"); %>
	<% List locations = (List)request.getAttribute("locationTypes"); %>
	<% Map event = (Map)request.getAttribute("event"); %>
	<% List locationsEvent = (List)request.getAttribute("locationsEvent"); %>
	<% List ticketsEvent = (List)request.getAttribute("ticketsEvent"); %>
  
	<br/>
	<h2>Detalle de Evento</h2>
	
	<table>
		<form method="GET" action="/tickets/domain.EventsUpdate" >
		<tr>
			<td>
				
				<table>
					<input type="hidden" name="code" value="<%=event.get("code")%>"/>
					<tr>
						<td>Nombre:</td><td><input type="text" name="name" value="<%= event.get("name")%>"/></td>
					</tr>
					<tr>
						<td>Tipo Evento:</td>
						<td>
							<select name="eventType">
								<% for(int i = 0, n = eventTypes.size(); i < n; i++) {
									Map eventType = (Map)eventTypes.get(i); 
									if((Integer)event.get("eventType") == (Integer)eventType.get("code")){%>
									<option value="<%= eventType.get("code")%>" selected><%= eventType.get("description")%></option>
									<%}else{%>
									<option value="<%= eventType.get("code")%>"><%= eventType.get("description")%></option>
								<%}}%>
							</select>
						</td>
					</tr>
					<tr>
						<td>Artistas:</td><td><input type="text" name="artists" value="<%= event.get("artists")%>"/></td>
					</tr>
					<tr>
						<td>Fecha:</td><td><input type="text" name="date" value="<%= event.get("date")%>"/></td>
					</tr>
					<tr>
						<td>Lugar:</td><td><input type="text" name="place" value="<%= event.get("place")%>"/></td>
					</tr>
				</table>
			</td>
			
			<td>
				<table align="top">
					<tr>
						<th colspan="3">Localidades</th>
					</tr>
					
					<%
					Boolean encontrado = false;
					for(int j = 0; j < locations.size(); j++) {
						encontrado = false;
						Map location = (Map)locations.get(j); 
						for(int k=0; k<locationsEvent.size(); k++){
							Map locationActual = (Map)locationsEvent.get(k);
							if((Integer)location.get("code") == (Integer)locationActual.get("type")){
								encontrado = true;
					%>
						<tr>
							<!--wdf?-->
							<td><input type="checkbox" name="locationType<%=location.get("code")%>" checked><%=location.get("description")%></input></td>
							<td><input type="checkbox" name="locationNumbered<%=location.get("code")%>" <%=((Boolean)locationActual.get("numbered") ? "checked" : "")%>>Numerada&nbsp;&nbsp;&nbsp;</input></td>
							<td>Precio:<input type="text" value="<%=locationActual.get("price")%>" name="locationPrice<%=location.get("code")%>"/></td>
							<td>Cantidad:<input type="text" value="<%=locationActual.get("quantity")%>" name="locationQuantity<%=location.get("code")%>"/></td>
						</tr>
					<%}}if(!encontrado){%>
						<tr>
							<td><input type="checkbox" name="locationType<%=location.get("code")%>"><%=location.get("description")%></input></td>
							<td><input type="checkbox" name="locationNumbered<%=location.get("code")%>">Numerada&nbsp;&nbsp;&nbsp;</input></td>
							<td>Precio:<input type="text" name="locationPrice<%=location.get("code")%>"/></td>
							<td>Cantidad:<input type="text" name="locationQuantity<%=location.get("code")%>"/></td>
						</tr>
					<%}}%>
				</table>
				
			</td>
		</tr>
		</form>
		<tr>
			<td colspan="2">
				<table width="50%">
					<tr><td colspan="3"><h2>Tiquetes Vendidos<h2></td></tr>
					
					<%for(int x = 0; x < locations.size(); x++) {
						Map locationType = (Map)locations.get(x);
					%>
						<tr><th colspan="3"><%=locationType.get("description")%></th></tr>
						<tr><th>Numero Tiquete</th><th>Fecha</th><th>Monto Total</th><th>Detalle</th></tr>
						<%for(int y=0; y<ticketsEvent.size(); y++){
							Map ticket = (Map)ticketsEvent.get(y);
							if((Integer)ticket.get("type") == (Integer)locationType.get("code")){%>
								<tr>
									<td align="center"><%=ticket.get("code")%></td>
									<td align="center"><%=ticket.get("date")%></td>
									<td align="center"><%=ticket.get("amount")%></td>
									<td align="center">
										<a href="/tickets/domain.TicketsInvoice?code=<%=ticket.get("code")%>&codigoEvento=<%= event.get("code")%>">
											<button>Factura</button>
										</a>
									</td>
								</tr>
						<%}}}%>	
				</table>
			</td>
		</tr>
		<tr><td colspan="2"><input type="submit" value="Guardar"/></td></tr>
	</table>
</html>