package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de localidades
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class LocationFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM LOCATIONS WHERE LOCATIONTYPEID=? AND EVENTID=?";
	private final static String findAllStatement = "SELECT * FROM LOCATIONS";
	private final static String findByEventStatement = "SELECT * FROM LOCATIONS WHERE EVENTID=?";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public LocationRowGateway create(){
		return LocationRowGateway.load(dataSource,null);
	}

	public LocationRowGateway find(int locationType, int event){
		List locations = jdbcTemplate.queryForList(findStatement,locationType,event);
		LocationRowGateway location = LocationRowGateway.load(dataSource,(Map)locations.get(0));
		return location;
	}

	public List<LocationRowGateway> findAll(){
		List result = new ArrayList();
		List locations = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<locations.size();i++)
		  result.add(LocationRowGateway.load(dataSource,(Map)locations.get(i)));
		return result;
	}
	
	public List<LocationRowGateway> findByEventId(int eventId){
		List result = new ArrayList();
		List locations = jdbcTemplate.queryForList(findByEventStatement, eventId);
		for (int i=0; i<locations.size();i++)
		  result.add(LocationRowGateway.load(dataSource,(Map)locations.get(i)));
		return result;
	}
}