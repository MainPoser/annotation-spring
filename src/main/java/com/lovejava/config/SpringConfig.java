package com.lovejava.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @author:tianyao
 * @date:2019-04-12 19:46
 */
@Configuration//配置类
@ComponentScan(basePackages = {"com.lovejava"})//扫描包
@PropertySource(value = "classpath:jdbc.properties")//加载配置文件
@EnableTransactionManagement//开启对事务注释的支持
public class SpringConfig {
    //使用value注解赋值
    @Value(value = "${user}")
    private String user;
    @Value(value = "${password}")
    private String password;
    @Value(value = "${driverClass}")
    private String driverClass;
    @Value(value = "${url}")
    private String url;

    /**
     * 创建dataSource对象
     *
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource creatDataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setUser(user);
        dataSource.setJdbcUrl(url);
        try {
            dataSource.setDriverClass(driverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 创建一个JdbcTemplate对象
     */
    @Bean(name = "template")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    /**
     * 创建一个txManage事务管理器
     */
    @Bean(name = "txManage")
    public DataSourceTransactionManager creatTxManage(@Qualifier(value = "dataSource") DataSource dataSource) {
        DataSourceTransactionManager txManage = new DataSourceTransactionManager(dataSource);
        return txManage;
    }
}
