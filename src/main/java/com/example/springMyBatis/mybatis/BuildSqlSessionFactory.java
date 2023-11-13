package com.example.springMyBatis.mybatis;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

import javax.sql.DataSource;

public class BuildSqlSessionFactory {

    public static void main(String[] args) {
    	
        // Get DataSource object.
        DataSource dataSource = BuildSqlSessionFactory.getDataSource();
        // Creates a transaction factory.
        TransactionFactory trxFactory = new JdbcTransactionFactory();
        // Creates an environment object with the specified name, transaction
        // factory and a data source.
        Environment env = new Environment("dev", trxFactory, dataSource);
        // Creates a Configuration object base on the Environment object.
        // We can also add type aliases and mappers.
        Configuration config = new Configuration(env);
        TypeAliasRegistry aliases = config.getTypeAliasRegistry();
        aliases.registerAlias("record", Record.class);
        config.addMapper(RecordMapper.class);
        // Build the SqlSessionFactory based on the created Configuration object.
        // Open a session and query a record using the RecordMapper.
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        try (SqlSession session = factory.openSession()) {
            RecordMapper mapper = session.getMapper(RecordMapper.class);
            Record record = mapper.getRecord(1L);
            System.out.println("Record = " + record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a DataSource object.
     *
     * @return a DataSource.
     */
    public static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/mybatis_db");
        dataSource.setUsername("music");
        dataSource.setPassword("s3cr*t");
        return dataSource;
    }
}