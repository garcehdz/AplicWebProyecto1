package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de tipos de Entradas
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class LocationTypeRowGateway {

	private static final String insertStatement = "INSERT INTO LOCATIONTYPES(CODE, DESCRIPTION) VALUES (?,?)";

	private static final String updateStatement = "UPDATE LOCATIONTYPES SET DESCRIPTION=?";

	private static final String deleteStatement = "DELETE FROM LOCATIONTYPES WHERE CODE=?";

	private int code;
	private String description;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	LocationTypeRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	LocationTypeRowGateway(int code, String description)
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
	public static LocationTypeRowGateway load(DataSource ds, Map map) {
		LocationTypeRowGateway loc=null;
		if (map==null)
		  loc = new LocationTypeRowGateway();
		else {
		  int code = ((Integer)map.get("code")).intValue();
		  String description = (String)map.get("description");
		  loc = new LocationTypeRowGateway(code, description);
		}
		loc.setDataSource(ds);
		return loc;
	}
}