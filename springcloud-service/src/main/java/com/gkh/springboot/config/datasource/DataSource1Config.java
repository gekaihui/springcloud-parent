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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Primary：指定为默认数据源，没有该注解会报错，系统找不到默认数据源
 * @MapperScan：扫描指定包的mapper作为对应数据源，建议每个数据源都用不同的包区分
 * @Qualifier：与@Autowired类似，用作区分如果存在多个实现类要指定要注入哪个 参数为指定Bean的name
 */

@Configuration
@MapperScan(basePackages = "com.gkh.springboot.mapper.primary", sqlSessionFactoryRef = "primarySqlSessionFactory")
public class DataSource1Config {

    /**
     * 生成数据源，@Primary注解声明为默认数据源
     * @return
     */
    @Bean(name="primaryDataSoure")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sqlSessionFactory
     * @param datasource
     * @return
     * @throws Exception
     */
    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSoure") DataSource datasource)
            throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径*/
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        /** 设置datasource*/
        bean.setDataSource(datasource);
        return bean.getObject();
    }

    /**
     * 配置事务管理
     * @param datasource
     * @return
     */
    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSoure") DataSource datasource){
        return new DataSourceTransactionManager(datasource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
