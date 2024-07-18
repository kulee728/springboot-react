package todo.common.config;

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

@Configuration //Configuration Annotation
@PropertySource("classpath:/config.properties")
/*
 * github에 올리지 않고, 이메일 비밀번호 등 암호화 설정을 가져옴
 * properties : ?  >> 자산. gitignore에 꼭 추가해주세요.
 * 
 */
public class DBConfig {

	@Autowired
	private ApplicationContext applicationContext; 
	//현재 만든 TodoList-Backend 웹 어플리케이션 프로젝트에 대한 구조 정보를 담고 있다.
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	// 연결된 DB를 스프링에서 인지하고 관리할 것을 표기
	@Bean
	public DataSource dataSource(HikariConfig config) {
		DataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}
	
	// 마이바티스 설정 추가
	@Bean
	public SqlSessionFactory sessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		//CRUD (select, insert, update, delete) 작성된 mapper 폴더 경로 설정
		//src/main/resources 하위 mappers 폴더 내 작성된 *mapper.xml 들을 바라보겠다
		//classpath = src/main/resources
		sfb.setMapperLocations(applicationContext.getResources("classpath:/mappers/**.xml"));
		
		//DTO 모델이 모여있는 패키지 설정
		//db 테이블 상 컬럼 명과 dto에 작성한 컬럼 이름이 다를 때 별칭과 컬럼명을 매핑시켜주기 위해
		//dto 위치를 설정
		sfb.setTypeAliasesPackage("todo.dto");
		
		//mybatis 에서 db, 칼럼에 어떤 설정을 해주고 설정 정보를 어디에 작성했는지
		//mybatis 설정 경로와 파일명 작성
		sfb.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		//추후 파일명, 경로가 변경된다면 변경된 경로나 파일명 작성
		return sfb.getObject();
	}
	
	@Bean //기본 SQL 실행한다음 insert, update, delete 같은 경우 commit / rollback 하기
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sf) {
		return new SqlSessionTemplate(sf);
	}
	
	//전반적인 commit, rollback 관리를 해주는 transaction manager
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource
			dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	/**
	 * SqlSessionTemplatae => insert select update delete
	 * DataSourceTransactionManager => SqlSessionTemplate Commit, Rollback
	 */
	
	
}
