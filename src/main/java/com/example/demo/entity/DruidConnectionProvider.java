package com.example.demo.entity;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.quartz.SchedulerException;
import org.quartz.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author
 * [Druid连接池的Quartz扩展类]
 */
public class DruidConnectionProvider implements ConnectionProvider {
	
	public static final int DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION = 120;
	@Getter @Setter public String driver;
	@Getter @Setter public String URL;
	@Getter @Setter public String user;
	@Getter @Setter public String password;
	@Getter @Setter public int maxConnection;
	@Getter @Setter public String validationQuery;
	@Getter @Setter private boolean validateOnCheckout;
	@Getter @Setter private int idleConnectionValidationSeconds;
	@Getter @Setter private DruidDataSource datasource;

	@Override
	public Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}
	
	@Override
	public void shutdown() {
		datasource.close();
	}
	@Override
	public void initialize() throws SQLException{
		if (this.URL == null) {
			throw new SQLException("DBPool could not be created: DB URL cannot be null");
		}
		
		if (this.driver == null) {
			throw new SQLException("DBPool driver could not be created: DB driver class name cannot be null!");
		}
		
		if (this.maxConnection < 0) {
			throw new SQLException("DBPool maxConnectins could not be created: Max connections must be greater than zero!");
		}
		
		datasource = new DruidDataSource();
		try{
			datasource.setDriverClassName(this.driver);
		} catch (Exception e) {
			try {
				throw new SchedulerException("Problem setting driver class name on datasource: " + e.getMessage(), e);
			} catch (SchedulerException e1) {
			}
		}
		
		datasource.setUrl(this.URL);
		datasource.setUsername(this.user);
		datasource.setPassword(this.password);
		datasource.setMaxActive(this.maxConnection);
		datasource.setMinIdle(1);
		datasource.setMaxWait(0);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(DEFAULT_DB_MAX_CACHED_STATEMENTS_PER_CONNECTION);
		
		if (this.validationQuery != null) {
			datasource.setValidationQuery(this.validationQuery);
			if(!this.validateOnCheckout){
				datasource.setTestOnReturn(true);
			}else{
				datasource.setTestOnBorrow(true);
			}
			datasource.setValidationQueryTimeout(this.idleConnectionValidationSeconds);
		}
	}
}
