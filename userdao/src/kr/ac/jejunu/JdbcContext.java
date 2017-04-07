package kr.ac.jejunu;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sieun on 2017. 4. 7..
 */
public class JdbcContext {

    private DataSource dataSource;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public User jdbcContextWithStatementStrategyForGet(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;


        try {
            //connectionMaker가 있어 애가 가져올 수 있도록
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);

        /*    //쿼리만들기
            preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement.setLong(1, id);
*/
            //쿼리실행
            resultSet = preparedStatement.executeQuery();


            //생성된결과를 객체 매핑

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        //결과를 리턴
        return user;
    }




    public Long jdbcContextWithStatementStarategyForInsert(StatementStrategy statementStrategy) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long id = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);
/*
            preparedStatement = connection.prepareStatement("insert into user(name, password) VALUE (?,?)");
            //1번째 칸에는 이름 2번째칸에는 비밀번호
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());*/

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            //Long id = getLastInsertId(connection);

            id = resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //아이디를 리턴한다
        return id;
    }



    public void jdbcContextWithStatementStrategyForUpdate(StatementStrategy statementStrategy) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.makeStatement(connection);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Long insert(String sql, Object[] params) {
        StatementStrategy statementStrategy = connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 1 ; i <= params.length; i++){
                preparedStatement.setObject(i, params[i -1]);
            }
            return preparedStatement;
        };

        return jdbcContextWithStatementStarategyForInsert(statementStrategy);
    }

    public void update(Object[] params, String sql) {
        StatementStrategy statementStrategy = connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= params.length; i++){
                preparedStatement.setObject(i, params[i - 1]);
            }
            return preparedStatement;
        };

        jdbcContextWithStatementStrategyForUpdate(statementStrategy);
    }



    public User queryForObject(String sql, Object[] params) {
        StatementStrategy statementStrategy = connection -> {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            for (int i = 1 ; i <= params.length; i++){
                preparedStatement.setObject(i, params[i -1]);
            }
            return preparedStatement;
        };

        return jdbcContextWithStatementStrategyForGet(statementStrategy);
    }










      /*  PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("delete from user where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;*/


}



