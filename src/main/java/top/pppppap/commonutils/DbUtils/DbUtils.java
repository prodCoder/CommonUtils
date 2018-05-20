package top.pppppap.commonutils.DbUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * TODO
 *
 * @author pppppap
 * @since 2018-05-19 下午 23:32
 */

public class DbUtils {
    public static DataSource dataSource = new ComboPooledDataSource("mysql");

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new SQLException("get connection failed");
        }
        return connection;
    }

    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void close(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void close(PreparedStatement pstmt) throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
    }

    public static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
        try {
            close(pstmt);
        } finally {
            close(conn);
        }
    }

    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) throws SQLException {
        try {
            close(rs);
        } finally {
            close(pstmt, conn);
        }
    }
}
