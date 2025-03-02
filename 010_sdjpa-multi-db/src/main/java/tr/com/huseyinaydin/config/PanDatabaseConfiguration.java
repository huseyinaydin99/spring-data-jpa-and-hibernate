package tr.com.huseyinaydin.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PanDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }
}