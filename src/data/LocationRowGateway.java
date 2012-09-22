package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Entradas
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class LocationRowGateway {

	private static final String insertStatement = "INSERT INTO LOCATIONS VALUES (?,?,?,?,?)";

	private static final String updateStatement = "UPDATE LOCATIONS SET PRICE=?, QUANTITY=? WHERE LOCATIONTYPEID=? AND EVENTID=? AND NUMBERED=?";

	private static final String deleteStatement = "DELETE FROM LOCATIONS WHERE LOCATIONTYPEID=? AND EVENTID=?";
	
	private static final String deleteByEventStatement = "DELETE FROM LOCATIONS WHERE EVENTID=?";
	
	private int locationType;
	private int event;
	private int price;
	private int quantity;
	private boolean numbered;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	LocationRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	LocationRowGateway(int locationType, int evt, int price, int quantity, boolean numbered)
	{
		this.locationType=locationType; 
		this.event=evt; 
		this.price=price;
		this.quantity=quantity;
		this.numbered=numbered;
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setLocationType(int locationType) {this.locationType = locationType;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getLocationType() {return locationType;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setEvent(int event) {this.event=event;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getEvent() {return event;}
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setPrice(int pr) {this.price=pr;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getPrice() {return price;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setQuantity(int quantity) {this.quantity=quantity;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getQuantity() {return quantity;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setNumbered(boolean numbered){this.numbered=numbered;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public boolean getNumbered() {return numbered;}
	
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
		  jdbcTemplate.update(insertStatement, locationType,event,price,quantity,numbered ? 1 : 0);
		return locationType;
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement,price,quantity,locationType,event,numbered ? 1 : 0);
	}

	/**
	 * elimina un registro en la tabla
	 */
	public void delete() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteStatement,locationType,event);
	}
	
	/**
	 * elimina un registro en la tabla
	 */
	public void deleteByEvent(int event) {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteByEventStatement,event);
	}

	/**
	 * Crea un objeto a partir de una fuente de datos
	 * @param fuente
	 * @param mapa de datos
	 * @return Objeto creado
	 */
	public static LocationRowGateway load(DataSource ds, Map map) {
		LocationRowGateway loc=null;
		if (map==null)
		  loc = new LocationRowGateway();
		else {
		  int locationType = ((Integer)map.get("locationTypeId")).intValue();
		  int event = ((Integer)map.get("eventId")).intValue();
		  int price = ((Integer)map.get("price")).intValue();
		  int quantity = ((Integer)map.get("quantity")).intValue();
		  boolean numbered = ((Integer)map.get("numbered")).intValue() == 1;
		  loc = new LocationRowGateway(locationType,event,price,quantity,numbered);
		}
		loc.setDataSource(ds);
		return loc;
	}
}