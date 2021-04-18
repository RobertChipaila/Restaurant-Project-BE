package ro.sdaboys.restaurantapp.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Bean
    public DataSource getDataSource() {
        // DataSource are implementare implicita de Builder DP
        return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/book_it")
                .username("root")
                .password("12345")
                .build();
    }

    //liquibase
    @Bean
    public SpringLiquibase getSpringLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(getDataSource());
        // LB se bazeaza pe ChangeLog -> de stat -> se vor scrie niste sql-uri pe care LB trebuie sa le execute
        springLiquibase.setChangeLog("classpath:liquibase-changelog.xml");
        return springLiquibase;
    }
}
