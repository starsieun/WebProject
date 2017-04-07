package kr.ac.jejunu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sieun on 2017. 3. 24..
 */

@Configuration
public class DaoFactory {

    //자기자신을 new 해주는 녀석
    @Bean
    public UserDao userDao(){

        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());

        return userDao;

    }

    @Bean
    public JejuConnectionMaker connectionMaker() {

        return new JejuConnectionMaker();
    }
}
