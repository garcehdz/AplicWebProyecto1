package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de clientes
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class CustomerFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM CUSTOMERS WHERE ID=?";
	private final static String findAllStatement = "SELECT * FROM CUSTOMERS";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public CustomerRowGateway create(){
		return CustomerRowGateway.load(dataSource,null);
	}

	public CustomerRowGateway find(String id){
		List customers = jdbcTemplate.queryForList(findStatement,id);
		CustomerRowGateway customer;
		if (!customers.isEmpty())
		{
			customer = CustomerRowGateway.load(dataSource,(Map)customers.get(0));
		}
		else
		{
			customer = null;
		}
		return customer;
	}

	public List<CustomerRowGateway> findAll(){
		List result = new ArrayList();
		List customers = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<customers.size();i++)
		  result.add(CustomerRowGateway.load(dataSource,(Map)customers.get(i)));
		return result;
	}
}