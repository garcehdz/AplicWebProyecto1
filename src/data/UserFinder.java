package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de usuarios
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class UserFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM USERS WHERE USERNAME=?";
	private final static String findAllStatement = "SELECT * FROM USERS";
	private final static String findRolesStatement = "SELECT DESCRIPTION FROM ROLES WHERE CODE = (SELECT ROLECODE FROM ROLESBYUSER WHERE USERNAME=?)";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public UserRowGateway create(){
		return UserRowGateway.load(dataSource,null);
	}

	public UserRowGateway find(String username){
		List users = jdbcTemplate.queryForList(findStatement,username);
		UserRowGateway user = null;
		if (users.size() > 0)
		{
			user = UserRowGateway.load(dataSource,(Map)users.get(0));
		}
		return user;
	}

	public List<UserRowGateway> findAll(){
		List result = new ArrayList();
		List users = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<users.size();i++)
		  result.add(UserRowGateway.load(dataSource,(Map)users.get(i)));
		return result;
	}
	
	public List<String> findRoles(String username){
		List result = new ArrayList();
		List roles = jdbcTemplate.queryForList(findRolesStatement, username);
		for (int i=0; i<roles.size();i++){
			Map m = (Map)roles.get(i);
			result.add((String)m.get("DESCRIPTION"));
		}
		return result;
	}
}