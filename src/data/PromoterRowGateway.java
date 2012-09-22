package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Promotores
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromoterRowGateway {

	private static final String insertStatement = "INSERT INTO PROMOTERS VALUES (?,?,?,?,?,?,?,?)";

	private static final String updateStatement = "UPDATE PROMOTERS SET NAME=?, ADDRESS=?, TELEPHONE=?, ACCOUNT=?, BANK=?, COMISSION=?, USERNAME=? WHERE CODE=?";

	private static final String deleteStatement = "DELETE FROM PROMOTERS WHERE CODE=?";

	private int code;
	private String name;
	private String address;
	private String telephone;
	private String account;
	private String bank;
	private int comission;
	private String username;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	public PromoterRowGateway() {}

	/**
	 * Constructor con parametros
	 */
	PromoterRowGateway(int code, String nam, String add, String tel, String acc, String bank, int com, String username)
	{
		this.code=code; 
		this.name=nam; 
		this.address=add;
		this.telephone=tel;
		this.account=acc;
		this.bank=bank;
		this.comission=com;
		this.username=username;
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
	public void setAddress(String add) {this.address=add;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getAddress() {return address;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setTelephone(String tel) {this.telephone=tel;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getTelephone() {return telephone;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setAccount(String account) {this.account=account;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getAccount() {return account;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setBank(String bank) {this.bank=bank;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getBank() {return bank;}
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setComission(int comission) {this.comission=comission;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public int getComission() {return comission;}
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setUsername(String username) {this.username=username;}
	
	/**
	 * Accesor
	 * @return valor de la propiedad
	 */
	public String getUsername() {return username;}

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
		  jdbcTemplate.update(insertStatement, code,name,address,telephone,account,bank,comission,username);
		return code;
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement, name,address,telephone,account,bank,comission,username,code);
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
	public static PromoterRowGateway load(DataSource ds, Map map) {
		PromoterRowGateway prom=null;
		if (map==null)
		  prom = new PromoterRowGateway();
		else {
		  int code = ((Integer)map.get("code")).intValue();
		  String name = (String)map.get("name");
		  String address = (String)map.get("address");
		  String telephone = (String)map.get("telephone");
		  String account = (String)map.get("account");
		  String bank = (String)map.get("bank");
		  int comission = ((Integer)map.get("comission")).intValue();
		  String username = (String)map.get("username");
		  prom = new PromoterRowGateway(code,name,address,telephone,account,bank,comission,username);
		}
		prom.setDataSource(ds);
		return prom;
	}
}