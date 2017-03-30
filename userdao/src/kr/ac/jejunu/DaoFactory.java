package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sieun on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao(){

        return new UserDao(connectionMaker());
    }

    @Bean
    public JejuConnectionMaker connectionMaker() {

        return new JejuConnectionMaker();
    }
}
