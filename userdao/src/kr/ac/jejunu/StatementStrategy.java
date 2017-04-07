package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by sieun on 2017. 3. 31..
 */
public interface StatementStrategy {

    PreparedStatement makeStatement(Connection connection) throws SQLException;

}
