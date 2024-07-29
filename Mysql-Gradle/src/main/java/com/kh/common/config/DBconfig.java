package com.kh.common.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@PropertySource("classpath:/config.properties")
public class DBconfig {

	@Autowired
	private ApplicationContext  applicationContext; //연결 주소 관리자 (xml 등 보유)
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") //연결 관리자
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean //객체 생성 DataBase 연결 역할. config에 대한 set을 해줄 수 있다.
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config); //연결 시작!
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource); //HikarinConfig 에서 받은 정보로 연결한 DB 연결 경로를 가져와서 사용
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		sfb.setTypeAliasesPackage("com.kh.dto"); //dto 패키지 - DB에 작성한 column 값과 DTO 작성 변수명을 대조
		
		//나중에 column 명을 DB는 underscore, dto 는 camelCase로 작성했을때 처럼 dto 용법에 맞추기 위한 config 설정
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		return sfb.getObject();
		
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	} //SQL 작업을 관리한다.
	
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource ds) {
		return new DataSourceTransactionManager(ds);
	} //commit, rollback 자동처리. 서버에서 DB에 온전히 저장 / 반영 할 수 있다.
	
	
	
	
	
	
}
