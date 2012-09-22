package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Tickets
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class TicketRowGateway {

	private static final String insertStatement = "INSERT INTO TICKETS VALUES (?,?,?,?,?,?,?)";

	private static final String updateStatement = "UPDATE TICKETS SET LOCATIONTYPEID=?, EVENTID=?, CUSTOMERID=?, DATE=?, QUANTITY=?, AMOUNT=? WHERE CODE=?";

	private static final String deleteStatement = "DELETE FROM TICKETS WHERE CODE=?";

	private int code;
	private int locationType;
	private int event;
	private String customer;
	private String date;
	private int quantity;
	private int amount;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	
	
	/**
	 * Constructor por defecto
	 */
	TicketRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	TicketRowGateway(int code, int locationType, int event, String customer, String date, int quantity, int amount)
	{
		this.code=code; 
		this.locationType=locationType; 
		this.event=event;
		this.customer=customer;
		this.date=date;
		this.quantity=quantity;
		this.amount=amount;
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
	public void setLocationType(int locationType) {this.locationType=locationType;}
	
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
	public void setCustomer(String customer) {this.customer=customer;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getCustomer() {return customer;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setDate(String date) {this.date=date;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getDate() {return date;}

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
	public void setAmount(int amount) {this.amount=amount;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getAmount() {return amount;}
	
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
		int code = generator.nextInt(1000000);
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertStatement, code,locationType,event,customer,date,quantity,amount);
		return code;
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement, locationType,event,customer,date,quantity,amount,code);
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
	public static TicketRowGateway load(DataSource ds, Map map) {
		TicketRowGateway ticket=null;
		if (map==null)
		  ticket = new TicketRowGateway();
		else {
		  int code = ((Integer)map.get("code")).intValue();
		  int locationType = ((Integer)map.get("locationTypeId")).intValue();
		  int event = ((Integer)map.get("eventId")).intValue();
		  String customer = (String)map.get("customerId");
		  String date = (String)map.get("date");
		  int quantity = ((Integer)map.get("quantity")).intValue();
		  int amount = ((Integer)map.get("amount")).intValue();
		  ticket = new TicketRowGateway(code,locationType,event,customer,date,quantity,amount);
		}
		ticket.setDataSource(ds);
		return ticket;
	}
}