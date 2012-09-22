<%@ page import="java.util.Map" %>

<html>
	<head>
		<title>Sistema de Tiquetes</title>
		<link href="/login.css" rel="stylesheet" type="text/css"/>
		<jsp:useBean id="error" type="java.lang.String" class="java.lang.String" />
	</head>
	<body>
		<form name="login" action="/tickets/domain.Login" method="get">
			<table id="tblLogin">
				<tr><th colspan="2" class="left">Inicio de sesi&oacute;n</th></tr>
				<tr>
					<td>Nombre de Usuario:</td>
					<td><input type="text" name="username"/></td>
				</tr>
				<tr>
					<td>Contrase&ntilde;a:</td>
					<td><input type="password" name="pass"/></td>
				</tr>
				<tr><td colspan="2" class="right"><input type="submit" value="Entrar" /></td></tr>
				<tr><td colspan="2"><%=error%></td></tr>
			</table>
			<input name="accion" type="hidden" value="Login"/>
		</form>
	</body>
</html>