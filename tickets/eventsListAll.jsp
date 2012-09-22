<%@ page import="java.util.*" %>

<html>
	<head>
		<title>Listado de eventos</title>
		<link href="/styles.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<h2>Listado de eventos</h2>
		<% List<Map> eventTypes = (List<Map>)request.getAttribute("eventTypes"); %>
		<% List<Map> events = (List<Map>)request.getAttribute("events"); %>
		<table>
			<tr><th>Nombre</th><th>Tipo</th><th>Artistas</th><th>Fecha</th><th>Lugar</th></tr>
			<% for (Map event : events) { %>
			<tr>
				<td><%=event.get("name")%></td>
				<td>
					<% for (Map eventType : eventTypes) { %>
					<% if ((Integer)event.get("eventType") == (Integer)eventType.get("code")) { %>
					<%=eventType.get("description")%>
					<% } %>
					<% } %>
				</td>
				<td><%=event.get("artists")%></td>
				<td><%=event.get("date")%></td>
				<td><%=event.get("place")%></td>
				<td>
					<a href="/tickets/domain.TicketsFormNew?event=<%=event.get("code")%>">
						<button>Comprar</button>
					</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>