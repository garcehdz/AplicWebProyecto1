package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de tipos de eventos
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventTypeFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM EVENTTYPES WHERE CODE=?";
	private final static String findAllStatement = "SELECT * FROM EVENTTYPES";

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EventTypeRowGateway create(){
		return EventTypeRowGateway.load(dataSource, null);
	}

	public EventTypeRowGateway find(String code){
		List events = jdbcTemplate.queryForList(findStatement,code);
		EventTypeRowGateway event = EventTypeRowGateway.load(dataSource,(Map)events.get(0));
		return event;
	}

	public List<EventTypeRowGateway> findAll(){
		List result = new ArrayList();
		List events = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<events.size();i++)
		  result.add(EventTypeRowGateway.load(dataSource,(Map)events.get(i)));
		return result;
	}
}