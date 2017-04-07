package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sieun on 2017. 3. 24..
 */

//implement를 안해줬기 떄문에 안돌아감 JejuConnection부분을 생서앻줌
public interface ConnectionMaker {

    public Connection getConnection() throws ClassNotFoundException, SQLException;

    }
