package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de localidades numeradas
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class NumberedLocationFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findAllStatement = "SELECT * FROM NUMBEREDLOCATION";
	//private final static String findByEventIdStatement = "SELECT NL.LOCATIONNUMBER, NL.TICKETID FROM NUMBEREDLOCATION NL INNER JOIN TICKETS TK ON NL.TICKETID=TK.CODE";
	private final static String findByTicketIdStatement = "SELECT * FROM NUMBEREDLOCATION WHERE TICKETID=?";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public NumberedLocationRowGateway create(){
		return NumberedLocationRowGateway.load(dataSource,null);
	}

	//public PromoterRowGateway find(int code){
	//	List promoters = jdbcTemplate.queryForList(findStatement,code);
	//	PromoterRowGateway promoter = PromoterRowGateway.load(dataSource,(Map)promoters.get(0));
	//	return promoter;
	//}

	public List<NumberedLocationRowGateway> findAll(){
		List result = new ArrayList();
		List numberedLocations = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<numberedLocations.size();i++)
		  result.add(NumberedLocationRowGateway.load(dataSource,(Map)numberedLocations.get(i)));
		return result;
	}
	
	//public List<NumberedLocationRowGateway> findByEventId(int id){
	//	List result = new ArrayList();
	//	List numberedLocations = jdbcTemplate.queryForList(findByEventIdStatement, id);
	//	for (int i=0; i<numberedLocations.size();i++)
	//	  result.add(NumberedLocationRowGateway.load(dataSource,(Map)numberedLocations.get(i)));
	//	return result;
	//}
	
	public List<NumberedLocationRowGateway> findByTicketId(int id){
		List result = new ArrayList();
		List numberedLocations = jdbcTemplate.queryForList(findByTicketIdStatement, id);
		for (int i=0; i<numberedLocations.size();i++)
		  result.add(NumberedLocationRowGateway.load(dataSource,(Map)numberedLocations.get(i)));
		return result;
	}
}