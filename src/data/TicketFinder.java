package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de tickets
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class TicketFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM TICKETS WHERE CODE=?";
	private final static String findAllStatement = "SELECT * FROM TICKETS";
	private final static String findByEventStatement = "SELECT * FROM TICKETS WHERE EVENTID=?";
	private final static String findByEventAndLocationStatement = "SELECT * FROM TICKETS WHERE EVENTID=? AND LOCATIONTYPEID=?";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public TicketRowGateway create(){
		return TicketRowGateway.load(dataSource,null);
	}

	public TicketRowGateway find(int code){
		List tickets = jdbcTemplate.queryForList(findStatement,code);
		TicketRowGateway ticket = TicketRowGateway.load(dataSource,(Map)tickets.get(0));
		return ticket;
	}

	public List<TicketRowGateway> findAll(){
		List result = new ArrayList();
		List tickets = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<tickets.size();i++)
		  result.add(TicketRowGateway.load(dataSource,(Map)tickets.get(i)));
		return result;
	}
	
	public List<TicketRowGateway> findByEvent(int event){
		List result = new ArrayList();
		List tickets = jdbcTemplate.queryForList(findByEventStatement, event);
		for (int i=0; i<tickets.size();i++)
		  result.add(TicketRowGateway.load(dataSource,(Map)tickets.get(i)));
		return result;
	}
	
	public List<TicketRowGateway> findByEventAndLocation(int event, int locationType){
		List result = new ArrayList();
		List tickets = jdbcTemplate.queryForList(findByEventAndLocationStatement, event, locationType);
		for (int i=0; i<tickets.size();i++)
		  result.add(TicketRowGateway.load(dataSource,(Map)tickets.get(i)));
		return result;
	}
}