package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;


@Configuration
@ConfigurationProperties(prefix = "bdp.datasource.druid")
@MapperScan(basePackages = BdpSysDataSource.PACKAGE,sqlSessionFactoryRef = "bdpSqlSessionFactory")
public class BdpSysDataSource {
    static final String PACKAGE = "com.example.demo.dao.bdp";
    static final String MAPPER_LOCATION = "classpath:mapper/bdp/*.xml";

    @Value("${bdp.datasource.url}")
    private String url;

    @Value("${bdp.datasource.username}")
    private String user;

    @Value("${bdp.datasource.password}")
    private String password;

    @Value("${bdp.datasource.driverClassName}")
    private String driverClass;

    @Value("${bdp.datasource.initialSize}")
    private Integer initialSize;

    @Value("${bdp.datasource.minIdle}")
    private Integer minIdle;

    @Value("${bdp.datasource.maxActive}")
    private Integer maxActive;

    @Value("${bdp.datasource.maxWait}")
    private Integer maxWait;

    @Bean(name = "bdpDataSource")
    @Primary
    public DataSource bdpDataSource() {
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

    @Bean(name = "bdpSqlSessionFactory")
    @Primary
    public SqlSessionFactory bdpSqlSessionFactory(@Qualifier("bdpDataSource") DataSource bdpDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(bdpDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(BdpSysDataSource.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
