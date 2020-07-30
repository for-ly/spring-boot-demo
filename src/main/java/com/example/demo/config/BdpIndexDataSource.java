package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;


@Configuration
@ConfigurationProperties(prefix = "index.datasource.druid")
@MapperScan(basePackages = BdpIndexDataSource.PACKAGE,sqlSessionFactoryRef = "indexSqlSessionFactory")
public class BdpIndexDataSource {
    static final String PACKAGE = "com.example.demo.dao.index";
    static final String MAPPER_LOCATION = "classpath:mapper/index/*.xml";

    @Value("${index.datasource.url}")
    private String url;

    @Value("${index.datasource.username}")
    private String user;

    @Value("${index.datasource.password}")
    private String password;

    @Value("${index.datasource.driverClassName}")
    private String driverClass;

    @Value("${index.datasource.initialSize}")
    private Integer initialSize;

    @Value("${index.datasource.minIdle}")
    private Integer minIdle;

    @Value("${index.datasource.maxActive}")
    private Integer maxActive;

    @Value("${index.datasource.maxWait}")
    private Integer maxWait;

    @Bean(name = "indexDataSource")
    public DataSource indexDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        return dataSource;
    }

    @Bean(name = "indexSqlSessionFactory")
    public SqlSessionFactory indexSqlSessionFactory(@Qualifier("indexDataSource") DataSource indexDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(indexDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(BdpIndexDataSource.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
