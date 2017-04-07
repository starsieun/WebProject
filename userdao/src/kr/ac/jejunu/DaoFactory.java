package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by sieun on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

    //자기자신을 new 해주는 녀석
    @Bean
    public UserDao userDao(){

        UserDao userDao = new UserDao();
        userDao.setJdbcTemplate(jdbcTemplate());

        return userDao;

    }
    @Bean
    public JdbcTemplate jdbcTemplate(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate();

    }

    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource();
    }
}
