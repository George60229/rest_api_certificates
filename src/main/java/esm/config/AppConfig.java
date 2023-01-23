package esm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySources({@PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-prod.properties")})
public class AppConfig {

    @Value("${url}")
    private String url;

    @Value("${myUsername}")
    private String dbUsername;

    @Value("${password}")
    private String dbPassword;

    @Value("${driver-class-name}")
    private String dbDriver;





    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(dbUsername);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setDriverClassName(dbDriver);

        return new HikariDataSource(hikariConfig);
    }

}
