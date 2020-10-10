package novel.mysql;

import java.sql.*;
import java.util.List;

/**
 * @author : zhangshuai
 * @date : 14:03 2020/9/30
 */
public class NovelDao {
    public static final String driver_class = "com.mysql.jdbc.Driver";
    public static final String driver_url = "jdbc:mysql://127.0.0.1/tarantula?useunicode=true&characterEncoding=utf8";
    public static final String user = "root";
    public static final String password = "root";

    private static Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    /**
     * Connection
     */
    public NovelDao() {
        try {
            conn = NovelDao.getConnInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式 线程同步
     */
    private static synchronized Connection getConnInstance() {
        if (conn == null) {
            try {
                Class.forName(driver_class);
                conn = DriverManager.getConnection(driver_url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("连接数据库成功");
        }
        return conn;
    }

    /**
     * close
     */
    public void close() {

        try {
            if (conn != null) {
                NovelDao.conn.close();
            }
            if (pst != null) {
                this.pst.close();
            }
            if (rst != null) {
                this.rst.close();
            }
            System.out.println("关闭数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * query
     */
    public ResultSet executeQuery(String sql, List<String> sqlValues) {
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            rst = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rst;
    }

    /**
     * update
     */
    public int executeUpdate(String sql, List<String> sqlValues) {
        int result = -1;
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * sql set value
     */
    private void setSqlValues(PreparedStatement pst, List<String> sqlValues) {
        for (int i = 0; i < sqlValues.size(); i++) {
            try {
                pst.setObject(i + 1, sqlValues.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
