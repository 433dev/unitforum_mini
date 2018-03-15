package ftt.unitforum.dao.unitforum.impl;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ftt.unitforum.dao.unitforum.IUnitforumArticleDao;
import ftt.unitforum.dao.unitforum.IUnitforumMasterDao;

@Configuration
public class UnitforumDaoBeanFactory {
	@Autowired
	private DataSource ds;

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate unitforumSessionTemplate() {
		try {
			Resource[] resources = new Resource[] { 
					new ClassPathResource("sql/unitforumMaster.xml"),
					new ClassPathResource("sql/unitforumArticle.xml"),
			};

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(ds);
			sqlSessionFactory.setMapperLocations(resources);

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Bean
	public IUnitforumMasterDao unitforumMasterDao() {
		SqlSessionTemplate sessionTemplate = unitforumSessionTemplate();
		return sessionTemplate.getMapper(IUnitforumMasterDao.class);
	}

	@Bean
	public IUnitforumArticleDao unitforumArticleDao() {
		SqlSessionTemplate sessionTemplate = unitforumSessionTemplate();
		return sessionTemplate.getMapper(IUnitforumArticleDao.class);
	}
}
