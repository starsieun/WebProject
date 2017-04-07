package kr.ac.jejunu;

import com.mysql.jdbc.Statement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by sieun on 2017. 3. 15..
 */

//런타임을 실행할때 만들어진다.

public class UserDao {


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    //변하는 것

    //user Dao에 new를 해버리면 다른 한라대가 하면 불가능함
    //던져버림 나머지를
    // private DataSource dataSource = new JejuConnectionMaker;


    // 클라이언드에게 던짐


    public User get(Long id) throws ClassNotFoundException, SQLException {

        String sql = "select * from user where id = ?";
        Object[] params = new Object[]{id};
        User user1 = null;
        try{
            user1 = jdbcTemplate.queryForObject(sql,params,(resultSet, i)->{
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));

                user.setPassword(resultSet.getString("password"));

                return user;
            });

        } catch ( DataAccessException e){
            e.printStackTrace();
        }

        return user1;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {

        String sql = "insert into user(name, password) VALUE (?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conneciton ->{
            PreparedStatement preparedStatement = conneciton.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            return preparedStatement;
        },keyHolder);

        return (Long) keyHolder.getKey();
    }


    public void updata(User user) {

        String sql = "update user set name = ?, password =? where id =?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcTemplate.update(sql, params);

    }

    public void delete(Long id) {

        Object[] params = new Object[]{id};
        String sql = "delete from user where id = ?";

        jdbcTemplate.update(sql, params);
    }

}






//잘 모르는 부분 어떻게 바뀔지 모르는 뿐
//public abstract Connection getConnection()

 /*   private Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");

        //characterEncoding=utf-8

        Connection connection = DriverManager.getConnection("jdbc:mysql://117.17.102.106/user?character=utf-8","root","1234");
    }*/



