package kr.ac.jejunu;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by sieun on 2017. 3. 15..
 */
public class UserDaoTest {

    @Test
    public void get() throws ClassNotFoundException, SQLException {

        Long id = 11L;
        String name = "이시은";
        String password = "1111";

        UserDao userDao = new UserDao();
        User user = userDao.get(id);
        assertThat(id, is(user.getId()));
        assertThat(name, is(user.getName()));
        assertThat(password, is(user.getPassword()));

    }







}
