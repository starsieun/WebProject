package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sieun on 2017. 3. 15..
 */
public class UserDaoTest {

    UserDao userDao;

    ;

    @Before // 테스트전에 실행하라
    public void setup() {

//        daoFactory = new DaoFactory();
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
          ApplicationContext context = new GenericXmlApplicationContext("daoFactory.xml");
        userDao = context.getBean("userDao", UserDao.class);

    }

    @Test
    public void get() throws ClassNotFoundException, SQLException {

        Long id = 1L;
        String name = "이시은";
        String password = "1111";

        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));

    }

    @Test

    public void add() throws SQLException, ClassNotFoundException {

        String name = "시봉이";
        String password = "1111";
        User user = new User();
        user.setName(name);
        user.setPassword(password);

        Long id = userDao.add(user);
        User resultUser = userDao.get(id);

        assertThat(id, is(resultUser.getId()));
        assertThat(name, is(resultUser.getName()));
        assertThat(password, is(resultUser.getPassword()));
    }


}
