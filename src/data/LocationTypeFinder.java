package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de tipos de localidades
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class LocationTypeFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM LOCATIONTYPES WHERE CODE=?";
	private final static String findAllStatement = "SELECT * FROM LOCATIONTYPES";

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public LocationTypeRowGateway create(){
		return LocationTypeRowGateway.load(dataSource, null);
	}

	public LocationTypeRowGateway find(String code){
		List locations = jdbcTemplate.queryForList(findStatement,code);
		LocationTypeRowGateway location = LocationTypeRowGateway.load(dataSource,(Map)locations.get(0));
		return location;
	}

	public List<LocationTypeRowGateway> findAll(){
		List result = new ArrayList();
		List locations = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<locations.size();i++)
		  result.add(LocationTypeRowGateway.load(dataSource,(Map)locations.get(i)));
		return result;
	}
}