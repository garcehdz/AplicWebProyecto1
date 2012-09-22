package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de entradas numeradas
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class NumberedLocationRowGateway {

	private static final String insertStatement = "INSERT INTO NUMBEREDLOCATION(TICKETID, LOCATIONNUMBER) VALUES (?,?)";

	//private static final String updateStatement = "UPDATE LOCATIONTYPES SET locationNumber=?";

	private static final String deleteStatement = "DELETE FROM NUMBEREDLOCATION WHERE TICKETID=? AND LOCATIONNUMBER=?";

	private int ticketId;
	private int locationNumber;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	NumberedLocationRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	NumberedLocationRowGateway(int ticketId, int locationNumber)
	{
		this.ticketId=ticketId; 
		this.locationNumber=locationNumber; 
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setTicketId(int ticketId) {this.ticketId = ticketId;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getTicketId() {return ticketId;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setLocationNumber(int locationNumber) {this.locationNumber=locationNumber;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getLocationNumber() {return locationNumber;}

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
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertStatement, ticketId,locationNumber);
		return ticketId;
	}

	//public void update() {
	//	if (jdbcTemplate==null) createJdbcTemplate();
	//	  jdbcTemplate.update(updateStatement, locationNumber);
	//}

	/**
	 * elimina un registro en la tabla
	 */
	public void delete() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteStatement,ticketId);
	}

	/**
	 * Crea un objeto a partir de una fuente de datos
	 * @param fuente
	 * @param mapa de datos
	 * @return Objeto creado
	 */
	public static NumberedLocationRowGateway load(DataSource ds, Map map) {
		NumberedLocationRowGateway loc=null;
		if (map==null)
		  loc = new NumberedLocationRowGateway();
		else {
		  int ticketId = ((Integer)map.get("TICKETID")).intValue();
		  int locationNumber = ((Integer)map.get("LOCATIONNUMBER")).intValue();
		  loc = new NumberedLocationRowGateway(ticketId, locationNumber);
		}
		loc.setDataSource(ds);
		return loc;
	}
}