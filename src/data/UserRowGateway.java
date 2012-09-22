package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Usuarios
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class UserRowGateway {

	private static final String insertStatement = "INSERT INTO USERS VALUES (?,?)";

	private static final String updateStatement = "UPDATE USERS SET PASS=? WHERE USERNAME=?";

	private static final String deleteStatement = "DELETE FROM USERS WHERE USERNAME=?";
	
	private static final String insertRoleStatement = "INSERT INTO ROLESBYUSER VALUES(?,?)";

	private String username;
	private String pass;

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	
	
	/**
	 * Constructor por defecto
	 */
	public UserRowGateway() {}
	
	/**
	 * Constructor con parametros
	 */
	UserRowGateway(String username, String pass)
	{
		this.username=username; 
		this.pass=pass; 
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setUsername(String username) {this.username = username;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getUsername() {return username;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setPass(String pass) {this.pass=pass;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getPass() {return pass;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setDataSource(DataSource dataSource) { this.dataSource = dataSource;}

	/**
	 * Crea un jdbc
	 * @return jdbc
	 */
	private void createJdbcTemplate() { jdbcTemplate = new JdbcTemplate(dataSource);}

	/**
	 * hace un registro en la tabla de roles
	 * @param rol
	 */
	public void insertRole(int role){
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertRoleStatement,username,role);
	}
	
	/**
	 * hace un registro en la tabla
	 */
	public void insert(){
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertStatement,username,pass);
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement,pass,username);
	}

	/**
	 * elimina un registro en la tabla
	 */
	public void delete() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteStatement,username);
	}

	/**
	 * Crea un objeto a partir de una fuente de datos
	 * @param fuente
	 * @param mapa de datos
	 * @return Objeto creado
	 */
	public static UserRowGateway load(DataSource ds, Map map) {
		UserRowGateway user=null;
		if (map==null)
		  user = new UserRowGateway();
		else {
		  String username = (String)map.get("username");
		  String pass = (String)map.get("pass");
		  user = new UserRowGateway(username,pass);
		}
		user.setDataSource(ds);
		return user;
	}
}