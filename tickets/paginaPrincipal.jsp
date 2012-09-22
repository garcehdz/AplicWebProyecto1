<%@ page import="java.util.Map" %>

<html>
	<head>
		<title>Sistema de Tiquetes</title>
		<link href="/styles.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<div id="mnuBackground"/>
		<div id="mnuPrincipal">
			<span class="title">Sistema de tiquetes&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<% HttpSession tester = request.getSession(false); %>
			<% Object Administrator = tester.getAttribute("Administrator"); %>
			<% Object Promoter = tester.getAttribute("Promoter"); %>
			<% String username = (String)tester.getAttribute("Username"); %>
			<% if (Administrator != null && (Boolean)Administrator) { %>
				<div><a href='/tickets/domain.PromotersList' target="mainFrame">Promotores</a></div>
			<% } %>
			<% if (Promoter != null && (Boolean)Promoter) { %>
				<div><a href='/tickets/domain.EventsListByPromoter' target="mainFrame">Eventos</a></div>
			<% } %>
			<div><a href='/tickets/domain.EventsListAll' target="mainFrame">Compras</a></div>
		</div>
		<div id="lnkLogon">
			<%if (username != null) {%>
			<div><a href="/tickets/domain.Login">Salir</a></div>
			<%} else {%>
			<div><a href="/login.jsp">Entrar</a></div>
			<%}%>
		</div>
		<div id="mnuContent">
			<iframe name="mainFrame" src='/tickets/domain.EventsListAll'/>
		</div>
	</body>
</html>