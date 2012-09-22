<%@ page import="java.util.*" %>

<html>
  <head>
    <title>Sistema de Tiquetes</title>
	<link href="/styles.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
	<% List<HashMap> locations = (List<HashMap>)request.getAttribute("locations");%>
	<% Map event = (Map)request.getAttribute("event"); %>
	<form method="GET" action="/tickets/domain.TicketsInsert">
		<h2>Compra de ticket para <%=event.get("name")%></h2>
		C&eacute;dula<br/>
		<input type="text" name="txtCostumerId"/><br/>
		Nombre<br/>
		<input type="text" name="txtCostumerName"/><br/>
		Direci&oacute;n<br/>
		<input type="text" name="txtCostumerAddress"/><br/>
		Telefono<br/>
		<input type="text" name="txtCostumerTelephone"/><br/>
		N&uacute;mero de tarjeta<br/>
		<input type="text" name="txtCostumerCardNumber"/><br/>
		Tipo de tarjeta<br/>
		<input type="text" name="txtCostumerCardType"/><br/>
		Ticket<br/>
		<select name="txtLocationTypeId">
			<% for (Map location : locations) {%>
			<option value="<%=location.get("code")%>"><%=location.get("description") + ((Boolean)location.get("numbered") ? " numerada" : "") + " " + location.get("available") + "/" + location.get("quantity")%></option>
			<% } %>
		</select>
		Numerada:
		<input type="text" name="txtNumberedTicked"/>
		Cantidad:
		<input type="text" name="txtTicketQuantity"/>
		<input type="hidden" name="txtTicketEventId" value="<%=event.get("code")%>"/>
		<br/>
		<input type="submit" value="Comprar"/>
	</form>
  </body>
</html>