package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Clase que sirve para las consultas sobre la tabla de promotores
 * @author Luis Roldan Chacon
 * @author Gilberth Arce Hernandez
 */
public class PromoterFinder {

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private final static String findStatement = "SELECT * FROM PROMOTERS WHERE CODE=?";
	private final static String findAllStatement = "SELECT * FROM PROMOTERS";
	private final static String findByUsername = "SELECT CODE FROM PROMOTERS WHERE USERNAME=?";
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public PromoterRowGateway create(){
		return PromoterRowGateway.load(dataSource,null);
	}

	public PromoterRowGateway find(int code){
		List promoters = jdbcTemplate.queryForList(findStatement,code);
		PromoterRowGateway promoter = PromoterRowGateway.load(dataSource,(Map)promoters.get(0));
		return promoter;
	}

	public List<PromoterRowGateway> findAll(){
		List result = new ArrayList();
		List promoters = jdbcTemplate.queryForList(findAllStatement);
		for (int i=0; i<promoters.size();i++)
		  result.add(PromoterRowGateway.load(dataSource,(Map)promoters.get(i)));
		return result;
	}
	
	public int findPromoterCodeByUsername(String username){
		return jdbcTemplate.queryForInt(findByUsername,username);
	}
}