package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de eventos
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class EventFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM EVENTS WHERE CODE=?";
	private final static String findAllStatement = "SELECT * FROM EVENTS";
	private final static String findByPromoterStatement = "SELECT * FROM EVENTS WHERE PROMOTERID=?";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public EventRowGateway create(){
		return EventRowGateway.load(dataSource,null);
	}

	public EventRowGateway find(int code){
		List events = jdbcTemplate.queryForList(findStatement,code);
		EventRowGateway event = EventRowGateway.load(dataSource,(Map)events.get(0));
		return event;
	}
	
	public List<EventRowGateway> findAll() throws Exception {
		List result = new ArrayList();
		List events = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<events.size();i++){
			result.add(EventRowGateway.load(dataSource,(Map)events.get(i)));
		}
		return result;
	}
	
	public List<EventRowGateway> findByPromoter(int promoterid) throws Exception {
		List result = new ArrayList();
		List events = jdbcTemplate.queryForList(findByPromoterStatement, promoterid);
		for (int i=0; i<events.size();i++){
			result.add(EventRowGateway.load(dataSource,(Map)events.get(i)));
		}
		return result;
	}
}