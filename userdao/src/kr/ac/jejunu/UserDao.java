package kr.ac.jejunu;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;


/**
 * Created by sieun on 2017. 3. 15..
 */

//런타임을 실행할때 만들어진다.

public class UserDao {


    public User get(Long id) throws ClassNotFoundException, SQLException {

        //Class 로딩해야하네
        Class.forName("com.mysql.jdbc.Driver");

        //커넥션맺기


        Connection connection = DriverManager.getConnection("jdbc:mysql://117.17.102.106/user","root","1234");

            //쿼리만들기
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
        preparedStatement.setLong(1, id);




            //쿼리실행

        ResultSet resultSet = preparedStatement.executeQuery();
            //생성된결과를 객체 매핑

        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

            //자원해지

        resultSet.close();
        preparedStatement.close();
        connection.close();

            //결과를 리턴

        return user;
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://117.17.102.106/user?character=utf-8","root","1234");



        PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name, password) VALUE (?,?)");

        preparedStatement.setString(1, user.getName());

        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();




        preparedStatement = connection.prepareStatement("select last_insert_id()");

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        Long id = resultSet.getLong(1);


        resultSet.close();

        preparedStatement.close();

        connection.close();


        return id;
    }
}
