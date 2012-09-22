package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de clientes
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class CustomerRowGateway {

	private static final String insertStatement = "INSERT INTO CUSTOMERS VALUES (?,?,?,?,?,?)";

	private static final String updateStatement = "UPDATE CUSTOMERS SET NAME=?, ADDRESS=?, TELEPHONE=?, CARDNUMBER=?, CARDTYPE=? WHERE ID=?";

	private static final String deleteStatement = "DELETE FROM CUSTOMERS WHERE ID=?";

	private String id;
	private String name;
	private String address;
	private String telephone;
	private String cardNumber;
	private String cardType;

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	CustomerRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	CustomerRowGateway(String id, String name, String address, String telephone, String cardNumber, String cardType)
	{
		this.id=id; 
		this.name=name; 
		this.address=address;
		this.telephone=telephone;
		this.cardNumber=cardNumber;
		this.cardType=cardType;
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setId(String id) {this.id = id;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getId() {return id;}

	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setName(String name) {this.name=name;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getName() {return name;}
	
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setAddress(String address) {this.address=address;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getAddress() {return address;}

	
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setTelephone(String telephone) {this.telephone=telephone;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getTelephone() {return telephone;}

	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setCardNumber(String cardNumber) {this.cardNumber=cardNumber;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getCardNumber() {return cardNumber;}

	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setCardType(String cardType) {this.cardType=cardType;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getCardType() {return cardType;}

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
	public void insert(){
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(insertStatement, id,name,address,telephone,cardNumber,cardType);
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement, name,address,telephone,cardNumber,cardType,id);
	}
	
	/**
	 * elimina un registro en la tabla
	 */
	public void delete() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(deleteStatement,id);
	}

	/**
	 * Crea un objeto a partir de una fuente de datos
	 * @param fuente
	 * @param mapa de datos
	 * @return Objeto creado
	 */
	public static CustomerRowGateway load(DataSource ds, Map map) {
		CustomerRowGateway customer=null;
		if (map==null)
		  customer = new CustomerRowGateway();
		else {
		  String id = (String)map.get("id");
		  String name = (String)map.get("name");
		  String address = (String)map.get("address");
		  String telephone = (String)map.get("telephone");
		  String cardNumber = (String)map.get("cardNumber");
		  String cardType = (String)map.get("cardType");
		  customer = new CustomerRowGateway(id,name,address,telephone,cardNumber,cardType);
		}
		customer.setDataSource(ds);
		return customer;
	}
}