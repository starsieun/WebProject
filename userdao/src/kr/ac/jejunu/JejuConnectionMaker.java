package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sieun on 2017. 3. 24..
 */
public class JejuConnectionMaker implements ConnectionMaker {


    private String id;
    private String password;
    private String url;
    private String className;

/*
    private String password = "1234";;
    private String className = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://117.17.102.106/user?character=utf-8";
    String root = "root";
*/


    @Override
    public Connection getConnection() throws
            ClassNotFoundException, SQLException {
        Class.forName(className);
        return DriverManager.getConnection(url, id, password);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
