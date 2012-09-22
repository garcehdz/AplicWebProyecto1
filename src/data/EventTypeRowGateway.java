package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Tipos de evento
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventTypeRowGateway {

	private static final String insertStatement = "INSERT INTO EVENTTYPES(CODE, DESCRIPTION) VALUES (?,?)";

	private static final String updateStatement = "UPDATE EVENTTYPES SET DESCRIPTION=?";

	private static final String deleteStatement = "DELETE FROM EVENTTYPES WHERE CODE=?";

	private int code;
	private String description;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	EventTypeRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	EventTypeRowGateway(int code, String description)
	{
		this.code=code; 
		this.description=description; 
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setCode(int code) {this.code = code;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getCode() {return code;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setDescription(String description) {this.description=description;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getDescription() {return description;}

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
	 * hace un registro en la tabla
	 */
	public int insert(){
		Random generator = new Random();
		int code = generator.nextInt();
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertStatement, code,description);
		return code;
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement, description);
	}

	/**
	 * elimina un registro en la tabla
	 */
	public void delete() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteStatement,code);
	}

	/**
	 * Crea un objeto a partir de una fuente de datos
	 * @param fuente
	 * @param mapa de datos
	 * @return Objeto creado
	 */
	public static EventTypeRowGateway load(DataSource ds, Map map) {
		EventTypeRowGateway ev=null;
		if (map==null)
		  ev = new EventTypeRowGateway();
		else {
		  int code = ((Integer)map.get("code")).intValue();
		  String description = (String)map.get("description");
		  ev = new EventTypeRowGateway(code, description);
		}
		ev.setDataSource(ds);
		return ev;
	}
}