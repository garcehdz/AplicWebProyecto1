package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para el acceso a una fila de datos de Eventos
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventRowGateway {

	private static final String insertStatement = "INSERT INTO EVENTS VALUES (?,?,?,?,?,?,?)";

	private static final String updateStatement = "UPDATE EVENTS SET NAME=?, EVENTTYPECODE=?, ARTISTS=?, DATE=?, PLACE=?, PROMOTERID=? WHERE CODE=?";

	private static final String deleteStatement = "DELETE FROM EVENTS WHERE CODE=?";

	private int code;
	private String name;
	private int eventType;
	private String artists;
	private String date;
	private String place;
	private int promoter;
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;	

	/**
	 * Constructor por defecto
	 */
	EventRowGateway() {};

	/**
	 * Constructor con parametros
	 */
	EventRowGateway(int code, String nam, int evt, String art, String acc, String place, int com)
	{
		this.code=code; 
		this.name=nam; 
		this.eventType=evt;
		this.artists=art;
		this.date=acc;
		this.place=place;
		this.promoter=com;
	}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setCode(int code) {this.code = code;}
	
	
	public int getCode() {return code;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setName(String name) {this.name=name;}
	
	
	public String getName() {return name;}
	
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setEventType(int evt) {this.eventType=evt;}
	
	
	public int getEventType() {return eventType;}

	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setArtists(String art) {this.artists=art;}
	
	
	public String getArtists() {return artists;}

	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setDate(String date) {this.date=date;}
	
	
	public String getDate() {return date;}

	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setPlace(String place) {this.place=place;}
	
	
	public String getPlace() {return place;}
	
	/**
	 * Mutador
	 * @param valor a cambiar
	 */
	public void setPromoter(int promoter) {this.promoter=promoter;}
	
	
	public int getPromoter() {return promoter;}

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
		  jdbcTemplate.update(insertStatement, code,name,eventType,artists,date,place,promoter);
		return code;
	}

	/**
	 * actualiza un registro en la tabla
	 */
	public void update() {
		if (jdbcTemplate==null) createJdbcTemplate();
		  jdbcTemplate.update(updateStatement, name,eventType,artists,date,place,promoter,code);
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
	public static EventRowGateway load(DataSource ds, Map map) {
		EventRowGateway ev=null;
		if (map==null)
		  ev = new EventRowGateway();
		else {
		  int code = ((Integer)map.get("code")).intValue();
		  String name = (String)map.get("name");
		  int eventType = ((Integer)map.get("eventTypeCode")).intValue();
		  String artists = (String)map.get("artists");
		  String date = (String)map.get("date");
		  String place = (String)map.get("place");
		  int promoter = ((Integer)map.get("promoterid")).intValue();
		  ev = new EventRowGateway(code,name,eventType,artists,date,place,promoter);
		}
		ev.setDataSource(ds);
		return ev;
	}
}