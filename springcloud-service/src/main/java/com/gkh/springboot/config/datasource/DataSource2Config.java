package com.gkh.springboot.config.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.gkh.springboot.mapper.secondary", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class DataSource2Config {

    @Bean(name = "secondDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDatasource") DataSource dataSource)
            throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径*/
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        /** 设置datasource*/
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager sourceTransactionManager(@Qualifier("secondDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondSqlSessionTemplate")
    public SqlSessionTemplate secondSqlSessionTemplate(@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

